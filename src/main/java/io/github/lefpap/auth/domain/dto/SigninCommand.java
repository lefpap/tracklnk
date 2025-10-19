package io.github.lefpap.auth.domain.dto;

public record SigninCommand(
    String email,
    String password
) {}
