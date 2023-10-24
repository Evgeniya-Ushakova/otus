package com.evg.otus.service;

import com.evg.otus.dto.order.request.CreateOrderRequest;
import com.evg.otus.dto.order.response.CreateOrderResponse;
import com.evg.otus.dto.order.response.GenerateOrderNumberResponse;

public interface OrderService {
    GenerateOrderNumberResponse generateOrderNumber(Long userId);

    CreateOrderResponse create(CreateOrderRequest request, String idempotencyKey);
}
