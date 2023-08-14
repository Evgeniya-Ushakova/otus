package com.evg.otus.exception;

import com.evg.otus.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j(topic = "EXCEPTION_HANDLER")
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({DataNotFoundException.class})
    public BaseResponse dataNotFoundException(DataNotFoundException e) {
        return new BaseResponse(e.getCode(), e.getMessage());
    }

}
