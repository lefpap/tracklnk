package io.github.lefpap.user.web.dto;

import java.time.Instant;
import java.util.UUID;

public record ApiUserResponse(
    UUID id,
    String email,
    String displayName,
    Instant createdAt
) {
}
