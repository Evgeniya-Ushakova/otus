package com.evg.otus.controller;

import com.evg.otus.dto.user.request.CreateUserRequest;
import com.evg.otus.dto.user.request.UpdateUserRequest;
import com.evg.otus.dto.user.response.GetUserResponse;
import com.evg.otus.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private static final String USER_PATH = "/{userId}";

    private final UserService userService;

    @PostMapping("")
    public void create(@RequestBody CreateUserRequest request) {
        userService.create(request);
    }

    @GetMapping(USER_PATH)
    public GetUserResponse get(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    @DeleteMapping(USER_PATH)
    public void delete(@PathVariable Long userId) {
        userService.delete(userId);
    }

    @PutMapping(USER_PATH)
    public void update(@PathVariable Long userId, @RequestBody UpdateUserRequest request) {
        userService.update(userId, request);
    }

}
