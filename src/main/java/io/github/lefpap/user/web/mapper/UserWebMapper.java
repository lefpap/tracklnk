package io.github.lefpap.user.web.mapper;

import io.github.lefpap.user.domain.dto.GetUserResult;
import io.github.lefpap.user.domain.dto.UpdateUserInfoCommand;
import io.github.lefpap.user.web.dto.ApiUserResponse;
import io.github.lefpap.user.web.dto.ApiUserUpdateRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

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

    public UpdateUserInfoCommand toCommand(UUID userId, ApiUserUpdateRequest req) {
        return new UpdateUserInfoCommand(
            userId,
            req.displayName()
        );
    }
}
