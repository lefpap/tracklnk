package io.github.lefpap.auth.web;

import io.github.lefpap.auth.domain.dto.AuthTokenResult;
import io.github.lefpap.auth.domain.dto.SigninCommand;
import io.github.lefpap.auth.domain.dto.SignupCommand;
import io.github.lefpap.auth.domain.dto.SignupResult;
import io.github.lefpap.auth.domain.service.AuthCookieService;
import io.github.lefpap.auth.domain.service.AuthService;
import io.github.lefpap.auth.web.dto.ApiSigninRequest;
import io.github.lefpap.auth.web.dto.ApiSigninResponse;
import io.github.lefpap.auth.web.dto.ApiSignupRequest;
import io.github.lefpap.auth.web.dto.ApiSignupResponse;
import io.github.lefpap.auth.web.mapper.AuthWebMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthCookieService cookieService;
    private final AuthWebMapper mapper;

    public AuthController(AuthService authService, AuthCookieService cookieService, AuthWebMapper mapper) {
        this.authService = authService;
        this.cookieService = cookieService;
        this.mapper = mapper;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiSignupResponse> signup(@Valid @RequestBody ApiSignupRequest req) {
        SignupCommand cmd = mapper.toCommand(req);
        SignupResult signup = authService.signup(cmd);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toWeb(signup));
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiSigninResponse> signin(@Valid @RequestBody ApiSigninRequest req) {
        SigninCommand cmd = mapper.toCommand(req);
        AuthTokenResult tokens = authService.signin(cmd);
        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, cookieService.createRefreshCookie(tokens.refreshToken()))
            .body(mapper.toWeb(tokens));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiSigninResponse> refresh(
        @CookieValue(name = "${app.jwt.refresh-cookie.name:refresh_token}", required = false) Jwt refreshToken) {
        if (refreshToken == null) {
            throw new BadCredentialsException("Missing refresh token");
        }

        // Validate refresh JWT (issuer/exp/aud)
        UUID userId = UUID.fromString(refreshToken.getSubject());

        // Stateless rotation: issue new access + new refresh and set cookie
        AuthTokenResult tokens = authService.refresh(userId);

        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, cookieService.createRefreshCookie(tokens.refreshToken()))
            .body(mapper.toWeb(tokens));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        return ResponseEntity.noContent()
            .header(HttpHeaders.SET_COOKIE, cookieService.clearRefreshCookie())
            .build();
    }

}
