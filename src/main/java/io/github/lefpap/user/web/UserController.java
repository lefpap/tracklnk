package io.github.lefpap.user.web;

import io.github.lefpap.user.domain.dto.GetUserResult;
import io.github.lefpap.user.domain.dto.UpdateUserInfoCommand;
import io.github.lefpap.user.domain.service.UserService;
import io.github.lefpap.user.web.dto.ApiUserResponse;
import io.github.lefpap.user.web.dto.ApiUserUpdateRequest;
import io.github.lefpap.user.web.mapper.UserWebMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/me")
    public ResponseEntity<Void> updateMe(@AuthenticationPrincipal Jwt jwt, @Valid @RequestBody ApiUserUpdateRequest req) {
        UUID userId = UUID.fromString(jwt.getSubject());
        UpdateUserInfoCommand cmd = mapper.toCommand(userId, req);
        service.updateUserInfo(cmd);
        return ResponseEntity.accepted().build();
    }
}
