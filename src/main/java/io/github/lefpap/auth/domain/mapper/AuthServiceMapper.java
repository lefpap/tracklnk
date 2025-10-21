package io.github.lefpap.auth.domain.mapper;

import io.github.lefpap.auth.domain.dto.SignupCommand;
import io.github.lefpap.user.domain.entity.UserEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthServiceMapper {

    public UserEntity toEntity(@NonNull SignupCommand cmd, PasswordEncoder encoder) {
        UserEntity user = new UserEntity();
        user.setEmail(cmd.email());
        user.setPasswordHash(encoder.encode(cmd.password()));
        user.setDisplayName(buildDisplayName(cmd.email()));
        return user;
    }

    private String buildDisplayName(String email) {
        int atIndex = email.indexOf('@');
        if (atIndex > 0) {
            return email.substring(0, atIndex);
        } else {
            return email.toLowerCase();
        }
    }

}
