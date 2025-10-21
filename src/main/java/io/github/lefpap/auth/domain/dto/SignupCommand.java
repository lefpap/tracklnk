package io.github.lefpap.auth.domain.dto;

public record SignupCommand(
    String email,
    String password
) {}
