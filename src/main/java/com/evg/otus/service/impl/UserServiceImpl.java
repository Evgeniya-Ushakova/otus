package com.evg.otus.service.impl;

import com.evg.otus.dto.BaseResponse;
import com.evg.otus.dto.user.request.CreateUserRequest;
import com.evg.otus.dto.user.request.UpdateUserRequest;
import com.evg.otus.dto.user.response.UserResponse;
import com.evg.otus.entity.User;
import com.evg.otus.enums.ErrorMessageCode;
import com.evg.otus.exception.BadRequestException;
import com.evg.otus.repository.UserRepository;
import com.evg.otus.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
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
    public UserResponse create(CreateUserRequest request) {
        if(isUserExists(request.getEmail())) {
            throw new BadRequestException(ErrorMessageCode.DUPLICATE_DATA.getCode(),
                    String.format("User with email = %s already exists", request.getEmail()));
        }

        User user = new User();
        user.setName(request.getUsername());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(request.getPassword());
        User savedUser = userRepository.save(user);
        return UserResponse.builder()
                .code(SUCCESS_CODE)
                .message("Create user succeed")
                .id(savedUser.getId())
                .username(savedUser.getName())
                .firstName(savedUser.getFirstName())
                .email(savedUser.getEmail())
                .build();
    }

    private boolean isUserExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserResponse getUser(Long userId) {
        User user = userRepository.findByIdOrElseThrow(userId);
        return UserResponse.builder()
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
    public UserResponse update(Long userId, UpdateUserRequest request) {
        User user = userRepository.findByIdOrElseThrow(userId);

        Optional.ofNullable(request.getFirstName()).ifPresent(user::setFirstName);
        Optional.ofNullable(request.getLastName()).ifPresent(user::setLastName);
        Optional.ofNullable(request.getEmail()).ifPresent(user::setEmail);
        Optional.ofNullable(request.getPhone()).ifPresent(user::setPhone);

        user = userRepository.save(user);
        return UserResponse.builder()
                .code(SUCCESS_CODE)
                .message("Updated succeed")
                .firstName(getParameter(request.getFirstName(), user.getFirstName()))
                .lastName(getParameter(request.getLastName(), user.getLastName()))
                .email(getParameter(request.getEmail(), user.getEmail()))
                .phone(getParameter(request.getPhone(), user.getPhone()))
                .build();
    }

    private String getParameter(String parameter, String savedParameter) {
        return StringUtils.isNotBlank(parameter) ? savedParameter : null;
    }


}
