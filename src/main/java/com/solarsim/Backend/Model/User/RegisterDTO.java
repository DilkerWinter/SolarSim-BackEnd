package com.solarsim.Backend.Model.User;

public record RegisterDTO(String email, String password, String name, UserRole role) {
}
