package io.github.lefpap.auth.lib;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;

@Component
public class StringToJwtConverter implements Converter<String, Jwt> {

    private final JwtDecoder jwtDecoder;

    public StringToJwtConverter(JwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }

    @Override
    public Jwt convert(String source) {
        if (source.isBlank()) {
            return null;
        }

        return jwtDecoder.decode(source);
    }
}
