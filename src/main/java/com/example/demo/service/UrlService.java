package com.example.demo.service;

import com.example.demo.domain.Url;
import com.example.demo.dto.UrlResponseDto;
import com.example.demo.repository.UrlRepository;
import com.example.demo.utils.Base62Converter;
import com.example.demo.utils.Sha512Converter;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UrlService {

    private UrlRepository urlRepository;
    private Base62Converter base62Converter = new Base62Converter();
    private Sha512Converter sha512Converter = new Sha512Converter();
    private static BigInteger bigInteger = new BigInteger("0");
    private static List<String> urlList = new ArrayList<>();

    @Autowired
    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public UrlResponseDto findByHashValue(String encodedValue) throws Exception {
        String decodedValue = base62Converter.decoding(encodedValue);
        log.info("Decoded Value {}",decodedValue);
        Optional<Url> result = urlRepository.findByhashvalue(decodedValue);
        return isEmpty(encodedValue, result);
    }

    public UrlResponseDto createUrl(String originUrl) throws NoSuchAlgorithmException, ValidationException {
        originUrl = checkUrl(originUrl);
        BigInteger id = bigInteger.add(BigInteger.valueOf(1));
        String convertedBySha512 = sha512Converter.convert512(id.toString());
        String extract10Char = randomPick10(convertedBySha512);
        while(urlRepository.existsByhashvalue(extract10Char))
            extract10Char = randomPick10(convertedBySha512);
        Url url = new Url(extract10Char,originUrl);
        urlRepository.save(url);
        String encodedStr = base62Converter.encoding(extract10Char);
        return UrlResponseDto.builder()
                .originUrl(originUrl)
                .encodedValue(encodedStr)
                .hashValue(extract10Char)
                .build();
    }

    public UrlResponseDto createUrlWithLogin(String originUrl) throws NoSuchAlgorithmException, ValidationException, UnsupportedEncodingException {
        originUrl = checkUrl(originUrl);
        String encodedUtf8 = URLEncoder.encode(originUrl, "UTF-8");
        String convertedBySha512 = sha512Converter.convert512(encodedUtf8);
        String extract10Char = convertedBySha512.substring(0,10);
        if(urlList.indexOf(extract10Char) != -1)
            throw new DuplicateKeyException(originUrl);
        urlList.add(extract10Char);
        Url url = new Url(extract10Char,originUrl);
        urlRepository.save(url);
        String encodedStr = base62Converter.encoding(extract10Char);
        return UrlResponseDto.builder()
                .originUrl(originUrl)
                .encodedValue(encodedStr)
                .hashValue(extract10Char)
                .build();
    }
    /**
     * 객체가 Null인지 아닌지 확인하는 함수
     */
    private UrlResponseDto isEmpty(String encodedValue, Optional<Url> result) throws NotFoundException {
        if (result.isEmpty())
            throw new NotFoundException(encodedValue);
        else
            return UrlResponseDto.builder()
                    .hashValue(result.get().getHashvalue())
                    .originUrl(result.get().getOriginurl())
                    .encodedValue(encodedValue)
                    .build();
    }
    /**
     * 64글자 중 랜덤으로 10개를 뽑아 반환
     * **/
    public String randomPick10(String sha512) {
        String res = "";
        for(int i=0;i < 10;i++) {
            int tmp = (int)(Math.random() * 60);
            res += sha512.charAt(tmp);
        }
        return res;
    }
    /**
     * OriginUrl이 알맞은 형식으로 들어왔는지 확인하는 로직
     * **/
    private String checkUrl(String originUrl) throws ValidationException {
        int index = originUrl.indexOf("://"); //앞의 SCHEME유무 확인
        String changedUrl = "";
        if (index == -1)
            originUrl = "https://" + originUrl;
        if (validateUrl(originUrl) == false) //SCHEME없으면 인증이 안되기 때문에 앞에서 확인해줌.
            throw new ValidationException(originUrl);
        return originUrl;
    }
    private boolean validateUrl(String originUrl) {
        UrlValidator urlValidator = new UrlValidator();
        return urlValidator.isValid(originUrl);
    }
}
