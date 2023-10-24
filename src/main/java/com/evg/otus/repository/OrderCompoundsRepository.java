package com.evg.otus.repository;

import com.evg.otus.entity.Order;
import com.evg.otus.entity.OrderCompound;
import com.evg.otus.entity.OrderProductKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderCompoundsRepository extends JpaRepository<OrderCompound, OrderProductKey> {
}
