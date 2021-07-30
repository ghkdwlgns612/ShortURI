package com.example.demo.error;

import com.example.demo.error.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ErrorResponse handlerException(Exception e) {
        return ErrorResponse.builder()
                .errmsg(e.getMessage())
                .statusCode(HttpStatus.NOT_ACCEPTABLE.value())
                .message("잘못된 URI입니다.")
                .build();
    }
}
