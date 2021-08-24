package com.example.demo.repository;

import com.example.demo.domain.Url;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public class RepositoryTest {

    private UrlRepository urlRepository;
    Url url = Url.builder()
            .hashvalue("1111111111")
            .name("tester")
            .originurl("https://naver.com")
            .build();

    @Autowired
    public RepositoryTest(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
        url.setId(BigInteger.valueOf(11L));
    }


    /**
     * id = 11, name = tester, hashvalue = 1111111111, originurl = https://naver.com
     */

    @Test
    public void findByHashValueTest() {
        Optional<Url> byhashvalue = urlRepository.findByhashvalue("1111111111");
        Assertions.assertThat(byhashvalue.get().getHashvalue()).isEqualTo(url.getHashvalue());
        Assertions.assertThat(byhashvalue.get().getOriginurl()).isEqualTo(url.getOriginurl());
        Assertions.assertThat(byhashvalue.get().getId()).isEqualTo(url.getId());
    }

    @Test
    public void existsByhashvalueTest() {
        boolean byhashvalue = urlRepository.existsByhashvalue("1111111111");
        Assertions.assertThat(byhashvalue).isTrue();
    }

    @Test
    public void existsBynameTest() {
        boolean tester1 = urlRepository.existsByname("tester");
        boolean tester2 = urlRepository.existsByname("a");
        Assertions.assertThat(tester1).isTrue();
        Assertions.assertThat(tester2).isFalse();
    }

    @Test //name을 찾고 originurl비교 할 때 사용. 나중에 통합테스트에서 확인
    public void existsByoriginurlTest() {
        boolean byoriginurl = urlRepository.existsByoriginurl("https://naver.com");
        Assertions.assertThat(byoriginurl).isTrue();
    }

    @Test
    public void findUrlTest() {
        Optional<Url> tester = urlRepository.findUrl("tester", "https://naver.com");
        Assertions.assertThat(tester.get().getHashvalue()).isEqualTo(url.getHashvalue());
        Assertions.assertThat(tester.get().getOriginurl()).isEqualTo(url.getOriginurl());
        Assertions.assertThat(tester.get().getId()).isEqualTo(url.getId());
    }

    @Test
    public void findAllUrlByNameTest() {
        List<Url> tester = urlRepository.findAllUrlByName("tester");
        for (Url url: tester) {
            System.out.println("url = " + url);
        }
    }
}
