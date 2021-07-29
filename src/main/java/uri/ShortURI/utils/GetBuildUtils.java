package uri.ShortURI.utils;

import uri.ShortURI.controller.uri.dto.uri.UriRequestDto;
import uri.ShortURI.controller.uri.dto.uri.UriResponsDto;
import uri.ShortURI.domain.Uri;

import java.util.Optional;

public class GetBuildUtils {

    public Uri getBuildUri(UriRequestDto uriRequestDto) {
        Uri uri = Uri.builder()
                .id(uriRequestDto.getId())
                .originuri(uriRequestDto.getOriginuri())
                .changeduri(uriRequestDto.getChangeduri())
                .build();
        return uri;
    }

    public UriResponsDto getBuild(Long id, String originuri, String changeduri) {
        return UriResponsDto.builder()
                .id(id)
                .originuri(originuri)
                .changeduri(changeduri)
                .build();
    }

    public UriResponsDto getBuild(Optional<Uri> uri) {
        return UriResponsDto.builder()
                .id(uri.get().getId())
                .originuri(uri.get().getOriginuri())
                .changeduri(uri.get().getChangeduri())
                .build();
    }

    public UriResponsDto getBuild(UriRequestDto uri) {
        return UriResponsDto.builder()
                .id(uri.getId())
                .originuri(uri.getOriginuri())
                .changeduri(uri.getChangeduri())
                .build();
    }
}
