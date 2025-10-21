package io.github.lefpap.auth.domain.service;

import io.github.lefpap.auth.config.JwtProperties;
import io.github.lefpap.user.domain.entity.UserEntity;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.InstantSource;

@Service
public class AuthTokenService {

    private final JwtProperties props;
    private final InstantSource time;

    private final JwtEncoder jwtEncoder;

    public AuthTokenService(JwtProperties props, InstantSource time, JwtEncoder jwtEncoder) {
        this.props = props;
        this.time = time;
        this.jwtEncoder = jwtEncoder;
    }

    public String generateToken(UserEntity user) {
        Instant now = time.instant();

        JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer(props.issuer())
            .issuedAt(now)
            .expiresAt(now.plus(props.accessTtl()))
            .subject(user.getId().toString())
            .claim("scope", "SCOPE_user")
            .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
