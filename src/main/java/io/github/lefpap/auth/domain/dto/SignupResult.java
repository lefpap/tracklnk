package io.github.lefpap.auth.domain.dto;

import java.util.UUID;

public record SignupResult(
    UUID userId
) {

    public static SignupResult id(UUID userId) {
        return new SignupResult(userId);
    }
}
