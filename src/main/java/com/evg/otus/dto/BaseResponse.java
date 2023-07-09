package com.evg.otus.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseResponse {

    private int code;
    private String message;

}
