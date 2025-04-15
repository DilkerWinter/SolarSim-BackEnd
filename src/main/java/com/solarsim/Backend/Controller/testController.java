package com.solarsim.Backend.Controller;

import com.solarsim.Backend.Model.User.EmailDTO;
import com.solarsim.Backend.Model.User.User;
import com.solarsim.Backend.Repository.UserRepository;
import com.solarsim.Backend.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("teste")
public class testController {

    @Autowired
    UserRepository userRepository;


    @GetMapping("/admin")
    public ResponseEntity getTesteAdmin(){
        return ResponseEntity.ok().build();
    }

    @PostMapping("/user")
    public ResponseEntity<UserDetails> getTesteUser(@RequestBody EmailDTO emailDTO) {
        UserDetails usuario = userRepository.findByEmail(emailDTO.getEmail());

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }


}
