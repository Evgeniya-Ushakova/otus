package com.evg.otus.controller;

import com.evg.otus.dto.BaseResponse;
import com.evg.otus.dto.user.request.CreateUserRequest;
import com.evg.otus.dto.user.request.UpdateUserRequest;
import com.evg.otus.dto.user.response.GetUserResponse;
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

    @PostMapping("")
    public BaseResponse create(@RequestBody @Valid @NonNull CreateUserRequest request) {
        return userService.create(request);
    }

    @GetMapping(USER_PATH)
    public GetUserResponse get(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    @DeleteMapping(USER_PATH)
    public BaseResponse delete(@PathVariable Long userId) {
        return userService.delete(userId);
    }

    @PutMapping(USER_PATH)
    public BaseResponse update(@PathVariable Long userId, @Valid @NonNull @RequestBody UpdateUserRequest request) {
        return userService.update(userId, request);
    }

}
