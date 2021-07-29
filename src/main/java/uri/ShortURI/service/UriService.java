package uri.ShortURI.service;

import uri.ShortURI.controller.uri.dto.uri.UriResponsDto;

public interface UriService {
    public UriResponsDto findByOrigin(String origin);
    public UriResponsDto save(String origin);
}
