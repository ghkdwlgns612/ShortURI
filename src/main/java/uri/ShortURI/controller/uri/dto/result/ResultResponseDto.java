package uri.ShortURI.controller.uri.dto.result;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
public class ResultResponseDto<T> {
    private Integer statusCode;
    private String message;
    private T data;
}
