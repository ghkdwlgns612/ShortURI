package uri.ShortURI.controller.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import uri.ShortURI.controller.error.dto.ErrorResponse;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ErrorResponse handlerException(Exception e) {
            return ErrorResponse.builder()
                    .message("URI를 찾을 수 없습니다.")
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .errmsg(e.getMessage())
                    .build();
    }

    @ExceptionHandler(value =IllegalArgumentException.class)
    public ErrorResponse handlerDuplicateUri(Exception e) {
            return ErrorResponse.builder()
                    .message("중복된 URI가 존재합니다. 조회 기능을 사용하세요.")
                    .statusCode(HttpStatus.NOT_ACCEPTABLE.value())
                    .errmsg(e.getMessage())
                    .build();
    }
}
