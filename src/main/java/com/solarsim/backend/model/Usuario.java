package com.solarsim.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@ToString
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    @NotBlank(message = "Username é obrigatório")
    @Size(min = 3, max = 50, message = "Username deve ter entre 3 e 50 caracteres")
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Password é obrigatório")
    @Size(min = 6, message = "Senha deve ter pelo menos 6 caracteres")
    private String password;

    @Column(name = "is_admin", nullable = false)
    private boolean isAdmin = false;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;
}
