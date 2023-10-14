package com.evg.otus.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class CommonException extends RuntimeException implements OtusException {

    private int code;
    private String message;

    public CommonException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

}
