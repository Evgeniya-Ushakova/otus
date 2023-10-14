package com.evg.otus.controller;

import com.evg.otus.dto.BaseResponse;
import com.evg.otus.dto.user.request.CreateUserRequest;
import com.evg.otus.dto.user.request.UpdateUserRequest;
import com.evg.otus.dto.user.response.UserResponse;
import com.evg.otus.service.AuthService;
import com.evg.otus.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {

    private static final String USER_PATH = "/{userId}";

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("")
    public UserResponse create(@RequestBody @Valid @NonNull CreateUserRequest request) {
        return userService.create(request);
    }

    @GetMapping(USER_PATH)
    public UserResponse get(@RequestHeader("x-auth-token") String authToken,
                            @PathVariable Long userId) {
        authService.checkAuth(authToken, userId);
        return userService.getUser(userId);
    }

    @DeleteMapping(USER_PATH)
    public BaseResponse delete(@RequestHeader("x-auth-token") String authToken,
                               @PathVariable Long userId) {
        authService.checkAuth(authToken, userId);
        return userService.delete(userId);
    }

    @PutMapping(USER_PATH)
    public UserResponse update(@RequestHeader("x-auth-token") String authToken,
                               @PathVariable Long userId,
                               @Valid @NonNull @RequestBody UpdateUserRequest request) {
        authService.checkAuth(authToken, userId);
        return userService.update(userId, request);
    }

}
