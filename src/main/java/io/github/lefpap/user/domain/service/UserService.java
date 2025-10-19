package io.github.lefpap.user.domain.service;

import io.github.lefpap.user.domain.dto.GetUserResult;
import io.github.lefpap.user.domain.mapper.UserServiceMapper;
import io.github.lefpap.user.domain.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository users;
    private final UserServiceMapper mapper;

    public UserService(UserRepository users, UserServiceMapper mapper) {
        this.users = users;
        this.mapper = mapper;
    }

    public GetUserResult getUser(UUID userId) {
        return users.findById(userId)
            .map(mapper::toResult)
            .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
}
