package io.github.lefpap.user.domain.mapper;

import io.github.lefpap.user.domain.dto.GetUserResult;
import io.github.lefpap.user.domain.entity.UserEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class UserServiceMapper {

    public GetUserResult toResult(@NonNull UserEntity user) {
        return new GetUserResult(
            user.getId(),
            user.getEmail(),
            user.getDisplayName(),
            user.getCreatedAt()
        );
    }
}
