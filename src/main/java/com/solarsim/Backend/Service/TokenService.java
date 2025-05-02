package com.solarsim.Backend.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.solarsim.Backend.Model.User.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
             Algorithm algorithm = Algorithm.HMAC256(secret);
             String Token = JWT.create()
                     .withIssuer("auth-api")
                     .withSubject(user.getUsername())
                     .withExpiresAt(getExpirationDate())
                     .sign(algorithm);
             return Token;
        }catch (JWTCreationException exception){
            throw new RuntimeException("Error while generating Token", exception);
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException exception){
            return "Invalid token";
        }
    }

    private Instant getExpirationDate() {
        return LocalDateTime.now().plusMonths(5).toInstant(ZoneOffset.of("-03:00"));
    }
}
