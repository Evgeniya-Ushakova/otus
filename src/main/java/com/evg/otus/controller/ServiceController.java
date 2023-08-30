package com.evg.otus.controller;

import com.evg.otus.dto.HealthResponse;
import io.micrometer.core.annotation.Timed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {

    @GetMapping("/health/")
    @Timed(value = "serviceController.health")
    public HealthResponse getHealth() {
        return new HealthResponse("OK");
    }
    
}
