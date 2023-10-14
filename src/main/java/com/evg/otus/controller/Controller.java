package com.evg.otus.controller;

import com.evg.otus.dto.HealthResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/health/")
    public HealthResponse getHealth() {
        return new HealthResponse("OK");
    }

    @GetMapping("/")
    public HealthResponse getBaseUrl() {
        return new HealthResponse("Hello! You in app! Send some request for work)");
    }
    
}
