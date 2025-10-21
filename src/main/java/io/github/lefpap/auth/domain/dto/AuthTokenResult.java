package io.github.lefpap.auth.domain.dto;

import org.springframework.security.oauth2.jwt.Jwt;

public record AuthTokenResult(
    Jwt accessToken,
    Jwt refreshToken
) {

    public static AuthTokenResult tokens(Jwt accessToken, Jwt refreshToken) {
        return new AuthTokenResult(accessToken, refreshToken);
    }
}
