package io.github.lefpap.auth.web;

import io.github.lefpap.auth.domain.dto.SigninCommand;
import io.github.lefpap.auth.domain.dto.SigninResult;
import io.github.lefpap.auth.domain.dto.SignupCommand;
import io.github.lefpap.auth.domain.dto.SignupResult;
import io.github.lefpap.auth.domain.service.AuthService;
import io.github.lefpap.auth.web.dto.ApiSigninRequest;
import io.github.lefpap.auth.web.dto.ApiSigninResponse;
import io.github.lefpap.auth.web.dto.ApiSignupRequest;
import io.github.lefpap.auth.web.dto.ApiSignupResponse;
import io.github.lefpap.auth.web.mapper.AuthWebMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService auth;
    private final AuthWebMapper mapper;

    public AuthController(AuthService auth, AuthWebMapper mapper) {
        this.auth = auth;
        this.mapper = mapper;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiSignupResponse> signup(@RequestBody ApiSignupRequest req) {
        SignupCommand cmd = mapper.toCommand(req);
        SignupResult signup = auth.signup(cmd);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toWeb(signup));
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiSigninResponse> signin(@RequestBody ApiSigninRequest req) {
        SigninCommand cmd = mapper.toCommand(req);
        SigninResult signin = auth.signin(cmd);
        return ResponseEntity.ok(mapper.toWeb(signin));
    }

}
