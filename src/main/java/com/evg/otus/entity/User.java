package com.evg.otus.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class User extends EntityBase<Long> {

    @Column(name = "USERNAME", columnDefinition = "VARCHAR(50)")
    private String username;
    @Column(name = "FIRST_NAME", columnDefinition = "VARCHAR(50")
    private String firstName;
    @Column(name = "LAST_NAME", columnDefinition = "VARCHAR(50")
    private String lastName;
    @Column(name = "EMAIL", columnDefinition = "VARCHAR(50")
    private String email;
    @Column(name = "PHONE", columnDefinition = "VARCHAR(50")
    private String phone;

}
