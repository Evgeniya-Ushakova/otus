package com.evg.otus.repository;

import com.evg.otus.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    boolean existsByUserIdAndOrderId(Long userId, String idempotencyKey);


}