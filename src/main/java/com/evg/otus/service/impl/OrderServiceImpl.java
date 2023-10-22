package com.evg.otus.service.impl;

import com.evg.otus.dto.order.request.CreateOrderRequest;
import com.evg.otus.dto.order.response.CreateOrderResponse;
import com.evg.otus.dto.order.response.GenerateOrderNumberResponse;
import com.evg.otus.entity.Order;
import com.evg.otus.entity.OrderProductKey;
import com.evg.otus.entity.User;
import com.evg.otus.enums.ErrorMessageCode;
import com.evg.otus.exception.BadRequestException;
import com.evg.otus.repository.OrderRepository;
import com.evg.otus.repository.UserRepository;
import com.evg.otus.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "ORDER_SERVICE")
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Override
    public GenerateOrderNumberResponse generateOrderNumber(Long userId) {
        User user = userRepository.findByIdOrElseThrow(userId);
        return GenerateOrderNumberResponse.builder()
                .orderNumber(user.getOrderCount())
                .build();
    }

    @Override
    @Transactional
    public CreateOrderResponse create(CreateOrderRequest request, String idempotencyKey) {
        if (orderRepository.existsByUserIdAndOrderId(request.getUserId(), idempotencyKey)) {
            throw new BadRequestException(ErrorMessageCode.BAD_REQUEST.getCode(),
                    String.format("Order %s already exists", idempotencyKey));
        }

        Order order = new Order();
        order.

        List<> orders = request.getProductIds()
                .stream()
                .map(productId -> {
                    OrderProductKey orderProductKey = new OrderProductKey();
                    orderProductKey.setProductId(productId);
                    orderProductKey.setOrderId();
                    return order;
                })
                .collect(Collectors.toList());
        orderRepository.saveAll(orders);
        user.setOrderCount(user.getOrderCount() + 1);
        user = userRepository.save(user);
        LOGGER.info("Count of orders: {}, userId: {}", user.getOrderCount(), user.getId());
        return CreateOrderResponse.builder()
                .orderId(request.getOrderId())
                .message("Order created succeed")
                .build();
    }

}
