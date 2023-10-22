package com.evg.otus.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(schema = "evg", name = "ORDER_COMPOUND")
public class OrderCompound {

    @EmbeddedId
    private OrderProductKey id;

    @ManyToOne
    @JoinColumn(name = "ID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "ID")
    private Product product;

    private Long count;

}
