package com.solarsim.Backend.Repository.UserRepository;

import com.solarsim.Backend.Model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String> {
    UserDetails findUserDetailsByEmail(String email);

    User findUserByEmail(String email);
}
