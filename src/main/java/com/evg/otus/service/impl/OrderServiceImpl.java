package com.evg.otus.service.impl;

import com.evg.otus.dto.order.ProductDto;
import com.evg.otus.dto.order.request.CreateOrderRequest;
import com.evg.otus.dto.order.response.CreateOrderResponse;
import com.evg.otus.dto.order.response.GenerateOrderNumberResponse;
import com.evg.otus.entity.*;
import com.evg.otus.enums.ErrorMessageCode;
import com.evg.otus.exception.BadRequestException;
import com.evg.otus.repository.OrderCompoundsRepository;
import com.evg.otus.repository.OrderRepository;
import com.evg.otus.repository.ProductRepository;
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


    private final OrderCompoundsRepository orderCompoundsRepository;
    private final ProductRepository productRepository;
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
        if (orderRepository.existsByUserIdAndOrderKey(request.getUserId(), idempotencyKey)) {
            throw new BadRequestException(ErrorMessageCode.BAD_REQUEST.getCode(),
                    String.format("Order %s already exists for user %s", idempotencyKey, request.getUserId()));
        }

        User user = userRepository.findByIdOrElseThrow(request.getUserId());
        user.setOrderCount(user.getOrderCount() + 1);
        user = userRepository.save(user);

        Order order = new Order();
        order.setOrderKey(idempotencyKey);
        order.setTotalPrice(request.getTotalPrice());
        order.setUserId(user.getId());
        orderRepository.save(order);

        List<OrderCompound> orderCompounds = getProducts(request.getProducts(), order);
        order.setProducts(orderCompounds);
        orderRepository.save(order);

        LOGGER.info("Count of orders: {}, userId: {}", user.getOrderCount(), user.getId());
        return CreateOrderResponse.builder()
                .orderId(order.getId())
                .orderKey(idempotencyKey)
                .message("Order created succeed")
                .build();
    }

    private List<OrderCompound> getProducts(List<ProductDto> productDtos,
                                            Order order) {
        List<OrderCompound> orderCompounds = productDtos
                .stream()
                .map(productDto -> {
                    OrderProductKey orderProductKey = new OrderProductKey();
                    orderProductKey.setProductId(productDto.getId());
                    orderProductKey.setOrderId(order.getId());
                    OrderCompound orderCompound = new OrderCompound();
                    orderCompound.setId(orderProductKey);
                    orderCompound.setCount(productDto.getCount());
                    orderCompound.setOrder(order);
                    orderCompound.setProduct(productRepository.findByIdOrElseThrow(productDto.getId()));
                    return orderCompound;
                })
                .collect(Collectors.toList());
        return orderCompoundsRepository.saveAll(orderCompounds);
    }

}
