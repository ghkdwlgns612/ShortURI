package com.example.demo.error;


import com.example.demo.dto.ErrorResponse;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityExistsException;
import javax.xml.bind.ValidationException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@ControllerAdvice
@RestController
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse handlerNotFoundException(Exception e) {
        log.info(e.toString());
        return ErrorResponse.builder()
                .err(e.getMessage())
                .statusCode(HttpStatus.NOT_ACCEPTABLE.value())
                .message("잘못된 URI입니다.")
                .build();
    }

    @ExceptionHandler(ValidationException.class)
    public ErrorResponse handlerValidationException(Exception e) {
        log.info(e.toString());
        return ErrorResponse.builder()
                .err(e.getMessage())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message("유효하지 않은 URI입니다.")
                .build();
    }

    @ExceptionHandler(NoSuchAlgorithmException.class)
    public ErrorResponse handlerNoSuchAlgorithmException(Exception e) {
        log.info(e.toString());
        return ErrorResponse.builder()
                .err(e.getMessage())
                .statusCode(HttpStatus.FORBIDDEN.value())
                .message("이 환경에서는 암호화 할 수 없습니다.")
                .build();
    }

    @ExceptionHandler(UnsupportedEncodingException.class)
    public ErrorResponse handlerUnsupportedEncodingException(Exception e) {
        log.info(e.toString());
        return ErrorResponse.builder()
                .err(e.getMessage())
                .statusCode(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .message("인코딩 할 수 없는 값입니다.")
                .build();
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ErrorResponse handlerDuplicateKeyException(Exception e) {
        log.info(e.toString());
        return ErrorResponse.builder()
                .err(e.getMessage())
                .statusCode(HttpStatus.NOT_ACCEPTABLE.value())
                .message("중복 오류입니다. 다시 한 번 입력해주세요.")
                .build();
    }

    @ExceptionHandler(EntityExistsException.class)
    public ErrorResponse handlerEntityExistsException(Exception e) {
        log.info(e.toString());
        return ErrorResponse.builder()
                .err(e.getMessage())
                .statusCode(HttpStatus.EXPECTATION_FAILED.value())
                .message("Entity가 이상합니다.")
                .build();
    }
}
