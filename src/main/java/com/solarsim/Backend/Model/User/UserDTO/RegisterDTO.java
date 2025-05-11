package com.solarsim.Backend.Model.User.UserDTO;

import com.solarsim.Backend.Model.User.UserRole;

public record RegisterDTO(String email, String password, String name, UserRole role) {
}
