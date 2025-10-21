package io.github.lefpap.auth.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ApiSignupRequest(
    @Email
    String email,

    @NotBlank
    String password
) {
}
