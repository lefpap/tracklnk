package io.github.lefpap.auth.domain.service;

import io.github.lefpap.auth.domain.dto.AuthTokenResult;
import io.github.lefpap.auth.domain.dto.SigninCommand;
import io.github.lefpap.auth.domain.dto.SignupCommand;
import io.github.lefpap.auth.domain.dto.SignupResult;
import io.github.lefpap.auth.domain.mapper.AuthServiceMapper;
import io.github.lefpap.user.domain.entity.UserEntity;
import io.github.lefpap.user.domain.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository users;
    private final AuthServiceMapper mapper;
    private final PasswordEncoder encoder;
    private final AuthTokenService tokenService;

    public AuthService(UserRepository users, AuthServiceMapper mapper, PasswordEncoder encoder, AuthTokenService tokenService) {
        this.users = users;
        this.mapper = mapper;
        this.encoder = encoder;
        this.tokenService = tokenService;
    }

    public SignupResult signup(SignupCommand cmd) {
        users.findByEmail(cmd.email()).ifPresent(_ -> {
            throw new IllegalArgumentException("Email already registered");
        });

        UserEntity user = mapper.toEntity(cmd, encoder);
        UserEntity newUser = users.save(user);
        return SignupResult.id(newUser.getId());
    }

    public AuthTokenResult signin(SigninCommand cmd) {
        var user = users.findByEmail(cmd.email())
            .filter(u -> encoder.matches(cmd.password(), u.getPasswordHash()))
            .orElseThrow(() -> new BadCredentialsException("Invalid credentials"));

        var accessToken = tokenService.generateAccessToken(user);
        var refreshToken = tokenService.generateRefreshToken(user);
        return AuthTokenResult.tokens(accessToken, refreshToken);
    }

    public AuthTokenResult refresh(UUID userId) {
        var user = users.findById(userId)
            .orElseThrow(() -> new BadCredentialsException("Invalid credentials"));

        var accessToken = tokenService.generateAccessToken(user);
        var refreshToken = tokenService.generateRefreshToken(user);
        return AuthTokenResult.tokens(accessToken, refreshToken);
    }
}
