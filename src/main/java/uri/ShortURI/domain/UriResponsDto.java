package uri.ShortURI.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UriResponsDto {
    private Long id;
    private String originuri;
    private String changeduri;

    @Builder
    public UriResponsDto(Long id, String originuri, String changeduri) {
        this.id = id;
        this.originuri = originuri;
        this.changeduri = changeduri;
    }
}
