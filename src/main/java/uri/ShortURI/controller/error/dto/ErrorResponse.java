package uri.ShortURI.controller.error.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
    private String message;
    private int statusCode;
    private String errmsg;
}
