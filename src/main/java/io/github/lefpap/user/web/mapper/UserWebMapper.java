package io.github.lefpap.user.web.mapper;

import io.github.lefpap.user.domain.dto.GetUserResult;
import io.github.lefpap.user.web.dto.ApiUserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserWebMapper {

    public ApiUserResponse toWeb(GetUserResult result) {
        return new ApiUserResponse(
            result.id(),
            result.email(),
            result.displayName(),
            result.createdAt()
        );
    }
}
