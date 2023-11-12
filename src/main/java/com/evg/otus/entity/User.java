package com.evg.otus.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "evg", name = "user")
@EqualsAndHashCode(callSuper = true)
public class User extends EntityBase<Long> {

    @Column(name = "NAME", columnDefinition = "VARCHAR(50)")
    private String name;
    @Column(name = "FIRST_NAME", columnDefinition = "VARCHAR(50)")
    private String firstName;
    @Column(name = "LAST_NAME", columnDefinition = "VARCHAR(50")
    private String lastName;
    @Column(name = "EMAIL", columnDefinition = "VARCHAR(50)")
    private String email;
    @Column(name = "PHONE", columnDefinition = "VARCHAR(50)")
    private String phone;
    @Column(name = "PASSWORD", columnDefinition = "VARCHAR(50)")
    private String password;
    @Column(name = "ORDER_COUNT", columnDefinition = "BIGINT")
    private Long orderCount;

}
