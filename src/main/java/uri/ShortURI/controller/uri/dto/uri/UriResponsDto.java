package uri.ShortURI.controller.uri.dto.uri;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UriResponsDto {
    private Long id;
    private String originuri;
    private String changeduri;
}
