package com.evg.otus.service;

import com.evg.otus.dto.user.request.CreateUserRequest;
import com.evg.otus.dto.user.request.UpdateUserRequest;
import com.evg.otus.dto.user.response.GetUserResponse;

public interface UserService {

    void create(CreateUserRequest request);

    GetUserResponse getUser(Long userId);

    void delete(Long userId);

    void update(Long userId, UpdateUserRequest request);
}
