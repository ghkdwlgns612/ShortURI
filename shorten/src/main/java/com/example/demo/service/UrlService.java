package com.example.demo.service;

import com.example.demo.domain.Url;
import com.example.demo.dto.UrlResponseDto;
import com.example.demo.repository.UrlRepository;
import com.example.demo.utils.Base62Converter;
import com.example.demo.utils.Sha512Converter;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
@Slf4j
public class UrlService {
    private UrlRepository urlRepository;
    private Base62Converter base62Converter = new Base62Converter();
    private Sha512Converter sha512Converter = new Sha512Converter();
    private static BigInteger bigInteger = new BigInteger("0");

    public UrlResponseDto findByHashValue(String hashvalue) throws Exception {
        Optional<Url> result = urlRepository.findByhashvalue(hashvalue);
        log.info("UrlRepo in Main {}",urlRepository);
        if (result.isEmpty())
            throw new NotFoundException(hashvalue);
        else
            return UrlResponseDto.builder()
                    .hashValue(result.get().getHashvalue())
                    .originUrl(result.get().getOriginurl())
                    .build();
    }

//    public UrlResponseDto createUrl(String originUrl) throws NoSuchAlgorithmException {
//        BigInteger id = bigInteger.add(BigInteger.valueOf(1));
//        String convertedBySha512 = sha512Converter.convert512(id.toString());
//        String extract10Char = randomPick10(convertedBySha512);
//        while(urlRepository.existsByhashvalue(extract10Char))
//            extract10Char = randomPick10(convertedBySha512);
//        Url url = new Url(extract10Char,originUrl);
//        urlRepository.save(url);
//        String encodedStr = base62Converter.encoding(extract10Char);
//
//    }

    public String randomPick10(String sha512) {
        String res = "";
        for(int i=0;i < 7;i++) {
            int tmp = (int)(Math.random() * 60);
            res += sha512.charAt(tmp);
        }
        return res;
    }
}
