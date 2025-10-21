package io.github.lefpap.auth.domain.dto;

public record SigninResult(
    String accessToken
) {

    public static SigninResult of(String accessToken) {
        return new SigninResult(accessToken);
    }
}
