package io.github.lefpap.user.web;

import io.github.lefpap.user.domain.dto.GetUserResult;
import io.github.lefpap.user.domain.service.UserService;
import io.github.lefpap.user.web.dto.ApiUserResponse;
import io.github.lefpap.user.web.mapper.UserWebMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService service;
    private final UserWebMapper mapper;

    public UserController(UserService service, UserWebMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/me")
    public ResponseEntity<ApiUserResponse> me(@AuthenticationPrincipal Jwt jwt) {
        UUID userId = UUID.fromString(jwt.getSubject());
        GetUserResult user = service.getUser(userId);
        ApiUserResponse web = mapper.toWeb(user);
        return ResponseEntity.ok(web);
    }
}
