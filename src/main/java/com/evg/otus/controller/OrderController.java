package com.evg.otus.controller;

import com.evg.otus.dto.order.request.CreateOrderRequest;
import com.evg.otus.dto.order.response.CreateOrderResponse;
import com.evg.otus.dto.order.response.GenerateOrderNumberResponse;
import com.evg.otus.service.OrderService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/order")
public class OrderController {

    private final static String IDEMPOTENCY_KEY_HEADER = "Idempotency-Key";

    private final OrderService orderService;

    @GetMapping("/generate-order-number")
    public GenerateOrderNumberResponse generateOrderNumber(@RequestParam Long userId) {
        return orderService.generateOrderNumber(userId);
    }

    @PostMapping("")
    public CreateOrderResponse create(@RequestHeader(IDEMPOTENCY_KEY_HEADER) String idempotencyKey,
                                      @RequestBody @Valid @NonNull CreateOrderRequest request) {
        return orderService.create(request, idempotencyKey);
    }

}
