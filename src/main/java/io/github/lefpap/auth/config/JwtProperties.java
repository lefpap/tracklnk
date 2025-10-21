package io.github.lefpap.auth.config;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;

@Validated
@ConfigurationProperties(prefix = "app.jwt")
public record JwtProperties(
    @NotEmpty String issuer,
    @NotNull Duration accessTtl,
    @NotNull Duration refreshTtl,
    @NotNull @Valid RSAKeyProperties rsa,
    @NotNull @Valid RefreshCookieProperties refreshCookie
) {

    public record RSAKeyProperties(@NotNull RSAPrivateKey privateKey, @NotNull RSAPublicKey publicKey) {
    }

    public record RefreshCookieProperties(
        @NotBlank String name,
        @NotBlank String path,
        @NotBlank String sameSite,
        @NotNull Boolean secure,
        String domain
    ) {
    }
}
