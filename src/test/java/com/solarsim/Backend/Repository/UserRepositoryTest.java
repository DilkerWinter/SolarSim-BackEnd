package com.solarsim.Backend.Repository;

import com.solarsim.Backend.Model.User.UserDTO.RegisterDTO;
import com.solarsim.Backend.Model.User.User;
import com.solarsim.Backend.Model.User.UserRole;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should get UserDetails sucessfully from DB")
    void findUserDetailsByEmailSucess() {
        RegisterDTO dto = new RegisterDTO("jhondoe@gmail.com", "123", "jhondoe", UserRole.USER);
        this.createUser(dto);

        UserDetails user =  this.userRepository.findUserDetailsByEmail("jhondoe@gmail.com");

        assertThat(user.getUsername()).isEqualTo("jhondoe@gmail.com");
    }

    @Test
    @DisplayName("Should get User sucessfully from DB")
    void findUserByEmailSucess() {
        RegisterDTO dto = new RegisterDTO("jhondoe@gmail.com", "123", "jhondoe", UserRole.USER);
        this.createUser(dto);

        User user =  this.userRepository.findUserByEmail("jhondoe@gmail.com");

        assertThat(user.getEmail()).isEqualTo("jhondoe@gmail.com");
        assertThat(user.getName()).isEqualTo("jhondoe");
        assertThat(user.getRole()).isEqualTo(UserRole.USER);

    }


    @Test
    @DisplayName("Should not get UserDetails from DB")
    void findUserDetailsByEmailFailure() {
        UserDetails user =  this.userRepository.findUserDetailsByEmail("jhondoe@gmail.com");
        assertThat(user).isNull();
    }

    @Test
    @DisplayName("Should not get User from DB")
    void findUserByEmailFailure() {
        User user = (this.userRepository.findUserByEmail("jhondoe@gmail.com"));
        assertThat(user).isNull();
    }

    private User createUser(RegisterDTO data){
        User newUser = new User(data.name(), data.email(), data.password(), data.role());
        this.entityManager.persist(newUser);
        return newUser;
    }
}