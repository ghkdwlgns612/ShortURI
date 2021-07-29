package uri.ShortURI.repository;

import uri.ShortURI.domain.Uri;
import uri.ShortURI.domain.UriRequestDto;
import uri.ShortURI.domain.UriResponsDto;

import java.util.Optional;

public interface UriRepository {
    public void save(UriRequestDto uriRequestDto);
    public Optional<Uri> findByOrigin(String originUri);
}
