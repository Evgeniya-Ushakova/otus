package com.evg.otus.service;

import com.evg.otus.dto.BaseResponse;
import com.evg.otus.dto.user.request.CreateUserRequest;
import com.evg.otus.dto.user.request.UpdateUserRequest;
import com.evg.otus.dto.user.response.GetUserResponse;

public interface UserService {

    BaseResponse create(CreateUserRequest request);

    GetUserResponse getUser(Long userId);

    BaseResponse delete(Long userId);

    BaseResponse update(Long userId, UpdateUserRequest request);
}
