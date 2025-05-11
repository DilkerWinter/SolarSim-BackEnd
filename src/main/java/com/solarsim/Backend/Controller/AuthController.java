package com.solarsim.Backend.Controller;

import com.solarsim.Backend.Model.User.UserDTO.AuthDTO;
import com.solarsim.Backend.Model.User.UserDTO.LoginResponseDTO;
import com.solarsim.Backend.Model.User.UserDTO.RegisterDTO;
import com.solarsim.Backend.Model.User.User;
import com.solarsim.Backend.Repository.UserRepository.UserRepository;
import com.solarsim.Backend.Service.AuthService.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if(this.userRepository.findUserDetailsByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(), data.email(), encryptedPassword, data.role());

        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
