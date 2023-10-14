package com.evg.otus.service;

public interface AuthService {

    void checkAuth(String authToken, Long userIdFromRequest);

}
