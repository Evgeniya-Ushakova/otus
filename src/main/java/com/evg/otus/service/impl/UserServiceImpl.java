package com.evg.otus.service.impl;

import com.evg.otus.dto.BaseResponse;
import com.evg.otus.dto.user.request.CreateUserRequest;
import com.evg.otus.dto.user.request.UpdateUserRequest;
import com.evg.otus.dto.user.response.GetUserResponse;
import com.evg.otus.entity.User;
import com.evg.otus.repository.UserRepository;
import com.evg.otus.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "USER_SERVICE")
public class UserServiceImpl implements UserService {

    private final static int SUCCESS_CODE = 0;

    private final UserRepository userRepository;

    @Override
    public BaseResponse create(CreateUserRequest request) {
        User user = new User();
        user.setName(request.getUsername());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        User savedUser = userRepository.save(user);
        return new BaseResponse(SUCCESS_CODE,
                String.format("Create %s %s saved succeed with id = %s",
                        savedUser.getName(),
                        savedUser.getFirstName(),
                        savedUser.getId()));
    }

    @Override
    public GetUserResponse getUser(Long userId) {
        User user = userRepository.findByIdOrElseThrow(userId);
        return GetUserResponse.builder()
                .id(user.getId())
                .username(user.getName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();
    }

    @Override
    public BaseResponse delete(Long userId) {
        userRepository.deleteById(userId);
        return new BaseResponse(SUCCESS_CODE, "deleted succeed");
    }

    @Override
    @Transactional
    public BaseResponse update(Long userId, UpdateUserRequest request) {
        User user = userRepository.findByIdOrElseThrow(userId);

        Optional.ofNullable(request.getFirstName()).ifPresent(user::setFirstName);
        Optional.ofNullable(request.getLastName()).ifPresent(user::setLastName);
        Optional.ofNullable(request.getEmail()).ifPresent(user::setEmail);
        Optional.ofNullable(request.getPhone()).ifPresent(user::setPhone);

        userRepository.save(user);
        return new BaseResponse(SUCCESS_CODE, "updated succeed");
    }

}
