package com.evg.otus.service;

import com.evg.otus.dto.BaseResponse;
import com.evg.otus.dto.user.request.CreateUserRequest;
import com.evg.otus.dto.user.request.UpdateUserRequest;
import com.evg.otus.dto.user.response.UserResponse;

public interface UserService {

    UserResponse create(CreateUserRequest request);

    UserResponse getUser(Long userId);

    BaseResponse delete(Long userId);

    UserResponse update(Long userId, UpdateUserRequest request);
}
