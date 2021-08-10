package uri.ShortURI.service;

import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uri.ShortURI.controller.dto.UriResponseDto;
import uri.ShortURI.domain.Uri;
import uri.ShortURI.repository.UriRepository;
import uri.ShortURI.utils.Base62ConverService;
import uri.ShortURI.utils.Sha512Converter;

import javax.xml.bind.ValidationException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
@Slf4j
public class UriService {

    private UriRepository uriRepository;
    private Base62ConverService base62ConverService = new Base62ConverService();
    private Sha512Converter sha512Converter = new Sha512Converter();

    @Autowired
    public UriService(UriRepository uriRepository) {
        this.uriRepository = uriRepository;
    }

    public UriResponseDto findByChangeUri(String changedUri) throws Exception {
        Optional<Uri> uri = uriRepository.findByChangedUri(changedUri);
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
        String changedUrl = "";
        if (index == -1)
            originUri = "https://" + originUri;
        if (checkUri(originUri) == false) //SCHEME없으면 인증이 안되기 때문에 앞에서 확인해줌.
            throw new ValidationException(originUri);
        changedUrl = convertUri(originUri);
        Uri uri = new Uri();
        uriRepository.save(uri);
        return UriResponseDto.builder()
                    .changedUri(uri.getChangeduri())
                    .originUri(uri.getOriginuri())
                    .build();
    }

    private String convertUri(String originUri) throws NoSuchAlgorithmException {
        String changedUrl;
        changedUrl = URLEncoder.encode(originUri, StandardCharsets.UTF_8); //Url인코딩
        changedUrl = sha512Converter.convert512(changedUrl);
        changedUrl = base62ConverService.changeBase62(changedUrl);
        return changedUrl;
    }

    private String convertUri(Long id) throws NoSuchAlgorithmException {
        String changedUrl;
        changedUrl = sha512Converter.convert512(id);
        changedUrl = base62ConverService.changeBase62(changedUrl);
        return changedUrl;
    }

    private boolean checkUri(String originUri) {
        UrlValidator urlValidator = new UrlValidator();
        return urlValidator.isValid(originUri);
    }
}
