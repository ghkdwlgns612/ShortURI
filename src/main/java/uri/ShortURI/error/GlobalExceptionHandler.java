package uri.ShortURI.error;

import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import uri.ShortURI.error.dto.ErrorResponse;
import javax.xml.bind.ValidationException;

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
}