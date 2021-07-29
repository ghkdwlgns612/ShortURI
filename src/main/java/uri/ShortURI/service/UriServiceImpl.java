package uri.ShortURI.service;

import org.hibernate.DuplicateMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uri.ShortURI.domain.Uri;
import uri.ShortURI.controller.uri.dto.uri.UriRequestDto;
import uri.ShortURI.controller.uri.dto.uri.UriResponsDto;
import uri.ShortURI.repository.UriRepository;
import uri.ShortURI.utils.GetBuildUtils;

import java.util.DuplicateFormatFlagsException;
import java.util.Optional;

@Service
@Transactional
public class UriServiceImpl implements UriService{

    private final UriRepository uriRepository;
    private final GetBuildUtils getBuildUtils;


    @Autowired
    public UriServiceImpl(UriRepository uriRepository, GetBuildUtils getBuildUtils) {
        this.uriRepository = uriRepository;
        this.getBuildUtils = getBuildUtils;
    }

    @Override
    public UriResponsDto findByOrigin(String origin) {
        Optional<Uri> uri = uriRepository.findByOrigin(origin.toString());
        if (uri == null)
            return getBuildUtils.getBuild(0L, origin.toString(), "일치하는 URI가 존재하지 않습니다.");
        else
            return getBuildUtils.getBuild(uri);
    }

    @Override
    public UriResponsDto save(String origin) {
        UriRequestDto uri = UriRequestDto.builder()
                .originuri(origin)
                .changeduri(changeUri(origin))
                .build();

        System.out.println(uriRepository.findByOrigin(uri.getOriginuri()));
        if (uriRepository.findByOrigin(uri.getOriginuri()).isEmpty())
            uriRepository.save(uri);
        else {throw new IllegalArgumentException();}
        UriResponsDto uriResponsDto = getBuildUtils.getBuild(uri);
        return uriResponsDto;
    }



    private String changeUri(String origin) {
        String BASE62_CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int BASE62 = 62;
        return origin + "success";
    }
}
