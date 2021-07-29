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
}
