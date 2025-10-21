package io.github.lefpap.user.web.dto;

import jakarta.validation.constraints.NotBlank;

public record ApiUserUpdateRequest(
    @NotBlank String displayName
) {}
