package com.evg.otus.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessageCode {

    DATA_NOT_FOUND(1);

    int code;

    ErrorMessageCode(int code) {
        this.code = code;
    }
}
