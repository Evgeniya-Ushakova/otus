package com.evg.otus.dto.user.response;

import com.evg.otus.dto.BaseResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class UserResponse extends BaseResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String username;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String firstName;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String lastName;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String email;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String phone;

}
