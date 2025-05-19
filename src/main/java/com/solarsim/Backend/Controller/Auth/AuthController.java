package com.solarsim.Backend.Controller.Auth;

import com.solarsim.Backend.Model.User.UserDTO.AuthDTO;
import com.solarsim.Backend.Model.User.UserDTO.LoginResponseDTO;
import com.solarsim.Backend.Model.User.UserDTO.RegisterDTO;
import com.solarsim.Backend.Service.AuthService.AuthManagerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthManagerService authManagerService;

    public AuthController(AuthManagerService authManagerService) {
        this.authManagerService = authManagerService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthDTO data) {
        return authManagerService.login(data);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO data) {
        return authManagerService.register(data);
    }
}
