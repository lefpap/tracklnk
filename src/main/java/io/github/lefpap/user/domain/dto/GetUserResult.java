package io.github.lefpap.user.domain.dto;

import java.time.Instant;
import java.util.UUID;

public record GetUserResult(
    UUID id,
    String email,
    String displayName,
    Instant createdAt
) {}
