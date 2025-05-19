package com.solarsim.Backend.Service.AuthService;

import com.solarsim.Backend.Model.User.User;
import com.solarsim.Backend.Model.User.UserDTO.AuthDTO;
import com.solarsim.Backend.Model.User.UserDTO.LoginResponseDTO;
import com.solarsim.Backend.Model.User.UserDTO.RegisterDTO;
import com.solarsim.Backend.Repository.UserRepository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthManagerService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthManagerService(AuthenticationManager authenticationManager,
                              UserRepository userRepository,
                              TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public ResponseEntity<LoginResponseDTO> login(AuthDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    public ResponseEntity<Void> register(RegisterDTO data) {
        if (userRepository.findUserDetailsByEmail(data.email()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409 Conflict
        }
        String encryptedPassword = passwordEncoder.encode(data.password());
        User newUser = new User(data.name(), data.email(), encryptedPassword, data.role());
        userRepository.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).build(); // 201 Created
    }
}
