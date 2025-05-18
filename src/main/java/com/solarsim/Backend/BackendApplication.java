package com.solarsim.Backend;

import com.solarsim.Backend.Controller.AuthController;
import com.solarsim.Backend.Model.User.User;
import com.solarsim.Backend.Model.User.UserDTO.RegisterDTO;
import com.solarsim.Backend.Model.User.UserRole;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
