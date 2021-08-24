package com.example.demo.error;

import com.example.demo.dto.ErrorResponse;
import javassist.NotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.dao.DuplicateKeyException;

import javax.persistence.EntityExistsException;
import javax.xml.bind.ValidationException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@JsonTest
public class ErrorTest {
    @Autowired
    private JacksonTester<ErrorResponse> json;
    GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    void NotFoundExceptionTest() {
        var error = new ErrorResponse("잘못된 URI입니다.",406,"이런 URL이 어디있니?");
        NotFoundException notFoundException = new NotFoundException("이런 URL이 어디있니?");
        ErrorResponse errorResponse = globalExceptionHandler.handlerNotFoundException(notFoundException);
        Assertions.assertThat(errorResponse.getErr()).isEqualTo(error.getErr());
        Assertions.assertThat(errorResponse.getMessage()).isEqualTo(error.getMessage());
        Assertions.assertThat(errorResponse.getStatusCode()).isEqualTo(error.getStatusCode());
    }

    @Test
    void ValidationExceptionTest() {
        var error = new ErrorResponse("유효하지 않은 URI입니다.",404,"인증 실패했어~");
        ValidationException notFoundException = new ValidationException("인증 실패했어~");
        ErrorResponse errorResponse = globalExceptionHandler.handlerValidationException(notFoundException);
        Assertions.assertThat(errorResponse.getErr()).isEqualTo(error.getErr());
        Assertions.assertThat(errorResponse.getMessage()).isEqualTo(error.getMessage());
        Assertions.assertThat(errorResponse.getStatusCode()).isEqualTo(error.getStatusCode());
    }
    @Test
    void NoSuchAlgorithmExceptionTest() {
        var error = new ErrorResponse("이 환경에서는 암호화 할 수 없습니다.",403,"이 환경에서는 암호화 안되!");
        NoSuchAlgorithmException notFoundException = new NoSuchAlgorithmException("이 환경에서는 암호화 안되!");
        ErrorResponse errorResponse = globalExceptionHandler.handlerNoSuchAlgorithmException(notFoundException);
        Assertions.assertThat(errorResponse.getErr()).isEqualTo(error.getErr());
        Assertions.assertThat(errorResponse.getMessage()).isEqualTo(error.getMessage());
        Assertions.assertThat(errorResponse.getStatusCode()).isEqualTo(error.getStatusCode());
    }
    @Test
    void UnsupportedEncodingExceptionTest() {
        var error = new ErrorResponse("인코딩 할 수 없는 값입니다.",415,"이 값은 인코딩을 못해ㅠㅠ");
        UnsupportedEncodingException notFoundException = new UnsupportedEncodingException("이 값은 인코딩을 못해ㅠㅠ");
        ErrorResponse errorResponse = globalExceptionHandler.handlerUnsupportedEncodingException(notFoundException);
        Assertions.assertThat(errorResponse.getErr()).isEqualTo(error.getErr());
        Assertions.assertThat(errorResponse.getMessage()).isEqualTo(error.getMessage());
        Assertions.assertThat(errorResponse.getStatusCode()).isEqualTo(error.getStatusCode());
    }
    @Test
    void DuplicateKeyExceptionTest() {
        var error = new ErrorResponse("중복 오류입니다. 다시 한 번 입력해주세요.",406,"와.. 이게 중복이라고?!");
        DuplicateKeyException notFoundException = new DuplicateKeyException("와.. 이게 중복이라고?!");
        ErrorResponse errorResponse = globalExceptionHandler.handlerDuplicateKeyException(notFoundException);
        Assertions.assertThat(errorResponse.getErr()).isEqualTo(error.getErr());
        Assertions.assertThat(errorResponse.getMessage()).isEqualTo(error.getMessage());
        Assertions.assertThat(errorResponse.getStatusCode()).isEqualTo(error.getStatusCode());
    }
    @Test
    void EntityExistsExceptionTest() {
        var error = new ErrorResponse("Entity가 이상합니다.",417,"Entity맵핑이 안되네..");
        EntityExistsException notFoundException = new EntityExistsException("Entity맵핑이 안되네..");
        ErrorResponse errorResponse = globalExceptionHandler.handlerEntityExistsException(notFoundException);
        Assertions.assertThat(errorResponse.getErr()).isEqualTo(error.getErr());
        Assertions.assertThat(errorResponse.getMessage()).isEqualTo(error.getMessage());
        Assertions.assertThat(errorResponse.getStatusCode()).isEqualTo(error.getStatusCode());
    }
}
