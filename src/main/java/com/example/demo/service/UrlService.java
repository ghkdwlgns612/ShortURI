package com.example.demo.service;

import com.example.demo.domain.Url;
import com.example.demo.dto.UrlResponseDto;
import com.example.demo.repository.UrlRepository;
import com.example.demo.utils.Base62Converter;
import com.example.demo.utils.MakeDto;
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
    private UrlCheckService urlCheckService;
    private MakeDto makeDto;
    private Base62Converter base62Converter = new Base62Converter();
    private Sha512Converter sha512Converter = new Sha512Converter();

    @Autowired
    public UrlService(UrlRepository urlRepository, UrlCheckService urlCheckService, MakeDto makeDto) {
        this.urlRepository = urlRepository;
        this.urlCheckService = urlCheckService;
        this.makeDto = makeDto;
    }

    public UrlResponseDto findByHashValue(String encodedValue) throws Exception { //인코딩 된 값을 디코딩하여 DB에서 찾아 바로 리다이렉트
        String decodedValue = base62Converter.decoding(encodedValue);
        Optional<Url> result = urlRepository.findByhashvalue(decodedValue);
        return urlCheckService.isEmpty(encodedValue, result);
    }

    public UrlResponseDto createUrl(String originUrl) throws NoSuchAlgorithmException, ValidationException, UnsupportedEncodingException {
        originUrl = urlCheckService.checkUrl(originUrl);
        Url url = new Url(null,originUrl);
        urlRepository.save(url); //원래 List를 했으나 프로그램 종료 시 꼬일 수 있기 때문에 ID를 얻기 위해서 처음 save를 해줌. 
        BigInteger id = url.getId();
        String convertedBySha512 = sha512Converter.convert512(id.toString());
        String extract10Char =  randomPick10(convertedBySha512);
        url.setHashvalue(extract10Char);
        urlRepository.save(url); //Hashing Value를 정한 후 다시 업데이트.(FLUSH)
        String encodedStr = base62Converter.encoding(extract10Char);
        return makeDto.makeUrlResponseDto(originUrl, extract10Char, encodedStr);
    }

    public UrlResponseDto createUrlWithLogin(String originUrl) throws NoSuchAlgorithmException, ValidationException, UnsupportedEncodingException {
        originUrl = urlCheckService.checkUrl(originUrl);
        originUrl = originUrl.toLowerCase();
        String extract10Char = makeExtract10Char(originUrl);
        Url url = new Url(extract10Char,originUrl);
        urlRepository.save(url);
        String encodedStr = base62Converter.encoding(extract10Char);
        return makeDto.makeUrlResponseDto(originUrl, extract10Char, encodedStr);
    }

    /**
     * UTF-8인코딩 -> 512SHA -> 0 ~ 10자르기를 통해 10개의 16진수문자를 추출함.
     * **/
    private String makeExtract10Char(String originUrl) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String encodedUtf8 = URLEncoder.encode(originUrl, "UTF-8");
        String convertedBySha512 = sha512Converter.convert512(encodedUtf8);
        String extract10Char = makeFront10Char(originUrl, convertedBySha512);
        return extract10Char;
    }

    /**
     * originUrl을 해싱한 16진수 64글자 중 앞의 10글자를 추출함.
     */
    private String makeFront10Char(String originUrl, String convertedBySha512) {
        String extract10Char = convertedBySha512.substring(0,10);
        if(urlRepository.existsByhashvalue(extract10Char))
            throw new DuplicateKeyException(originUrl);
        return extract10Char;
    }

    /**
     * id를 해싱한 16진수 64글자 중 랜덤 10문자를 뽑아내 40비트를 생성.
     * **/
    private String pickRandom10Char(String convertedBySha512) {
        String extract10Char = randomPick10(convertedBySha512);
        while(urlRepository.existsByhashvalue(extract10Char))
            extract10Char = randomPick10(convertedBySha512);
        return extract10Char;
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
}
