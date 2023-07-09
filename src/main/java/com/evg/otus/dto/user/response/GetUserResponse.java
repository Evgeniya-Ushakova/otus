package com.evg.otus.dto.user.response;

import com.evg.otus.dto.BaseResponse;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class GetUserResponse extends BaseResponse {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

}
