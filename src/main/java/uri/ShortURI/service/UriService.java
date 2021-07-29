package uri.ShortURI.service;

import uri.ShortURI.domain.UriResponsDto;

public interface UriService {
    public UriResponsDto findByOrigin(String origin);
    public UriResponsDto save(String origin);
}
