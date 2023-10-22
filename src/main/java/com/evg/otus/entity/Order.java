package com.evg.otus.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(schema = "evg", name = "order")
@EqualsAndHashCode(callSuper = true)
public class Order extends EntityBase<Long> {

    @Column(name = "USER_ID", columnDefinition = "BIGINT")
    private Long userId;
    @Column(name = "ORDER_ID", columnDefinition = "BIGINT")
    private Long orderId;
    @Column(name = "TOTAL_PRICE", columnDefinition = "money")
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "order")
    private List<OrderCompound> products;

}
