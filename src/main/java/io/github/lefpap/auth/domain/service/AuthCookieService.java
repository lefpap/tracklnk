package io.github.lefpap.auth.domain.service;

import io.github.lefpap.auth.config.JwtProperties;
import io.github.lefpap.auth.config.JwtProperties.RefreshCookieProperties;
import org.springframework.http.ResponseCookie;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.InstantSource;

@Service
public class AuthCookieService {

    private final JwtProperties props;
    private final InstantSource time;

    public AuthCookieService(JwtProperties props, InstantSource time) {
        this.props = props;
        this.time = time;
    }

    public String createRefreshCookie(Jwt token) {
        RefreshCookieProperties refreshCookie = props.refreshCookie();
        ResponseCookie cookie = ResponseCookie.from(refreshCookie.name(), token.getTokenValue())
            .httpOnly(true)
            .path(refreshCookie.path())
            .maxAge(Duration.between(time.instant(), token.getExpiresAt()))
            .sameSite(refreshCookie.sameSite())
            .secure(refreshCookie.secure())
            .build();

        return cookie.toString();
    }

    public String clearRefreshCookie() {
        RefreshCookieProperties refreshCookie = props.refreshCookie();
        var c = ResponseCookie.from(refreshCookie.name(), "")
            .httpOnly(true)
            .secure(refreshCookie.secure())
            .sameSite(refreshCookie.sameSite())
            .path(refreshCookie.path())
            .domain(refreshCookie.domain())
            .maxAge(Duration.ZERO)
            .build();

        return c.toString();
    }
}
