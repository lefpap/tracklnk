package io.github.lefpap.auth.web.mapper;

import io.github.lefpap.auth.domain.dto.SigninCommand;
import io.github.lefpap.auth.domain.dto.SigninResult;
import io.github.lefpap.auth.domain.dto.SignupCommand;
import io.github.lefpap.auth.domain.dto.SignupResult;
import io.github.lefpap.auth.web.dto.ApiSigninRequest;
import io.github.lefpap.auth.web.dto.ApiSigninResponse;
import io.github.lefpap.auth.web.dto.ApiSignupRequest;
import io.github.lefpap.auth.web.dto.ApiSignupResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class AuthWebMapper {

    public SigninCommand toCommand(@NonNull ApiSigninRequest req) {
        return new SigninCommand(
            req.email(),
            req.password()
        );
    }

    public SignupCommand toCommand(@NonNull ApiSignupRequest req) {
        return new SignupCommand(
            req.email(),
            req.password()
        );
    }

    public ApiSignupResponse toWeb(@NonNull SignupResult result) {
        return new ApiSignupResponse(
            result.userId()
        );
    }

    public ApiSigninResponse toWeb(@NonNull SigninResult result) {
        return new ApiSigninResponse(result.accessToken());
    }

}
