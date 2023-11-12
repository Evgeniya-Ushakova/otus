package com.evg.otus.dto.user.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Long orderCountToIncrease;

}
