package uri.ShortURI.error.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorResponse {
    private String message;
    private int statusCode;
    private String err;

    @Builder
    public ErrorResponse(String message, int statusCode, String err) {
        this.message = message;
        this.statusCode = statusCode;
        this.err = err;
    }
}