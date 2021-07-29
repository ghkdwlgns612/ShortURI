package uri.ShortURI.repository;

import uri.ShortURI.domain.Uri;
import uri.ShortURI.controller.uri.dto.uri.UriRequestDto;

import java.util.Optional;

public interface UriRepository {
    public void save(UriRequestDto uriRequestDto);
    public Optional<Uri> findByOrigin(String originUri);
}
