package uri.ShortURI.service;

import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uri.ShortURI.controller.dto.UriResponseDto;
import uri.ShortURI.domain.Uri;
import uri.ShortURI.repository.UriRepository;

import javax.xml.bind.ValidationException;
import java.util.Optional;

@Service
@Slf4j
public class UriService {

    private UriRepository uriRepository;
    private Base62ConverService convertService = new Base62ConverService();

    @Autowired
    public UriService(UriRepository uriRepository) {
        this.uriRepository = uriRepository;
    }

    public UriResponseDto findByChangeUri(String changedUri) throws Exception {
        Optional<Uri> uri = uriRepository.findBychangeduri(changedUri);
        log.info(changedUri);
        if (uri.isEmpty()) //요청 URI가 DB에 존재하지 않으면 예외 발생
            throw new NotFoundException(changedUri);
        else
            return UriResponseDto.builder() //DB정보들을 DTO에 만들어 컨트롤러에 전달.
                    .originUri(uri.get().getOriginuri())
                    .changedUri(uri.get().getChangeduri())
                    .build();
    }

    public UriResponseDto changeUri(String originUri) throws Exception {
        int index = originUri.indexOf("://"); //앞의 SCHEME유무 확인
        if (index == -1)
            originUri = "https://" + originUri;
        if (checkUri(originUri) == false) //SCHEME없으면 인증이 안되기 때문에 앞에서 확인해줌.
            throw new ValidationException(originUri);
        Long originNum = creatOriginNum(originUri); //URI 숫자 변경 후 난수발생.
        Uri uri = Uri.builder().
                originuri(originUri).
                changeduri(convertService.toBase62(originNum)).
                build();
        log.info(uri.getOriginuri());
        log.info(uri.getChangeduri());
        uriRepository.save(uri);
        return UriResponseDto.builder()
                    .changedUri(uri.getChangeduri())
                    .originUri(uri.getOriginuri())
                    .build();
    }

    private Long creatOriginNum(String originUri) {
        Long originNum = 0L;
        int i = 0;
        while (i < originUri.length())
            originNum += originUri.charAt(i++) + (int)(Math.random() * 1000000);
        return originNum;
    }

    private boolean checkUri(String originUri) {
        UrlValidator urlValidator = new UrlValidator();
        return urlValidator.isValid(originUri);
    }
}
