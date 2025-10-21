package io.github.lefpap.user.domain.dto;

import java.util.UUID;

public record UpdateUserInfoCommand(
    UUID userId,
    String displayName
) {
}
