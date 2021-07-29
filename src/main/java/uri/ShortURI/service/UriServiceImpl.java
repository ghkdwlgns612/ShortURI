package uri.ShortURI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uri.ShortURI.domain.Uri;
import uri.ShortURI.domain.UriRequestDto;
import uri.ShortURI.domain.UriResponsDto;
import uri.ShortURI.repository.UriRepository;

import java.util.Optional;

@Service
@Transactional
public class UriServiceImpl implements UriService{

    private final UriRepository uriRepository;

    @Autowired
    public UriServiceImpl(UriRepository uriRepository) {
        this.uriRepository = uriRepository;
    }

    @Override
    public UriResponsDto findByOrigin(String origin) {
        Optional<Uri> uri = uriRepository.findByOrigin(origin.toString());
        if (uri == null)
            return UriResponsDto.builder()
                    .id(0L)
                    .originuri(origin.toString())
                    .changeduri("일치하는 URI가 존재하지 않습니다.")
                    .build();
        else
            return UriResponsDto.builder()
                    .id(uri.get().getId())
                    .originuri(uri.get().getOriginuri())
                    .changeduri(uri.get().getChangeduri())
                    .build();
    }

    @Override
    public UriResponsDto save(String origin) {
        UriRequestDto uri = UriRequestDto.builder()
                .originuri(origin)
                .changeduri(changeUri(origin))
                .build();

        uriRepository.save(uri);

        UriResponsDto uriResponsDto = UriResponsDto.builder()
                .id(uri.getId())
                .originuri(uri.getOriginuri())
                .changeduri(uri.getChangeduri())
                .build();
        return uriResponsDto;
    }

    private String changeUri(String origin) {
        String BASE62_CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int BASE62 = 62;
        return origin + "success";
    }
}
