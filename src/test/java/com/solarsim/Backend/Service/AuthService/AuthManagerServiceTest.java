package com.solarsim.Backend.Service.AuthService;

import com.solarsim.Backend.Model.User.User;
import com.solarsim.Backend.Model.User.UserDTO.AuthDTO;
import com.solarsim.Backend.Model.User.UserDTO.LoginResponseDTO;
import com.solarsim.Backend.Model.User.UserDTO.RegisterDTO;
import com.solarsim.Backend.Model.User.UserRole;
import com.solarsim.Backend.Repository.UserRepository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthManagerServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private AuthManagerService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authService = new AuthManagerService(authenticationManager, userRepository, tokenService);
    }

    @Test
    @DisplayName("Should return JWT token when login is successful")
    void testLoginShouldReturnTokenWhenAuthenticated() {
        AuthDTO authDTO = new AuthDTO("test@example.com", "password123");
        User mockUser = new User("Test", "test@example.com", "encrypted", UserRole.USER);
        Authentication authentication = mock(Authentication.class);

        when(authentication.getPrincipal()).thenReturn(mockUser);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(tokenService.generateToken(mockUser)).thenReturn("mocked-token");

        ResponseEntity<LoginResponseDTO> response = authService.login(authDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("mocked-token", response.getBody().token());
    }

    @Test
    @DisplayName("Should create a new user and return 201 when email is not registered")
    void testRegisterShouldCreateUserWhenEmailNotExists() {
        RegisterDTO registerDTO = new RegisterDTO("Test User", "new@example.com", "password123", UserRole.USER);

        when(userRepository.findUserDetailsByEmail("new@example.com")).thenReturn(null);

        ResponseEntity<Void> response = authService.register(registerDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    @DisplayName("Should return 409 Conflict when email is already registered")
    void testRegisterShouldReturnConflictWhenEmailAlreadyExists() {
        RegisterDTO registerDTO = new RegisterDTO("Test User", "existing@example.com", "password123", UserRole.USER);
        User existingUser = new User("Existing", "existing@example.com", "pass", UserRole.USER);

        when(userRepository.findUserDetailsByEmail(anyString())).thenReturn(existingUser);

        ResponseEntity<Void> response = authService.register(registerDTO);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        verify(userRepository, never()).save(any(User.class));
    }

}
