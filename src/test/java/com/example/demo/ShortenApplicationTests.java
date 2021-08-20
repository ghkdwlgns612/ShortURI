package com.example.demo;

import com.example.demo.domain.Url;
import com.example.demo.dto.UrlResponseDto;
import com.example.demo.repository.UrlRepository;
import com.example.demo.service.UrlServiceImpl;
import com.example.demo.utils.Base62Converter;
import com.example.demo.utils.Sha512Converter;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.bind.ValidationException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;


@SpringBootTest
class ShortenApplicationTests {
	Logger log = (Logger) LoggerFactory.getLogger(ShortenApplicationTests.class);

	@Autowired
	private UrlRepository urlRepository;
	@Autowired
	private UrlServiceImpl urlServiceImpl;

	private Sha512Converter sha512Converter = new Sha512Converter();
	private Base62Converter base62Converter = new Base62Converter();
	private Base62 base62 = new Base62();


//	@Test
//	public void get10Char() {
//		String base16Char64 = "5aadb45520dcd8726b2822a7a78bb53d794f557199d5d4abdedd2c55a4bd6ca73607605c558de3db80c8e86c3196484566163ed1327e82e8b6757d1932113cb8";
//		String res = urlServiceImpl.randomPick10(base16Char64);
//		log.info(res);
//		String encoded = base62Converter.encoding("0000000001");
//		log.info(encoded);
//	}
	
	@Test
	public void findUrlAll() {
		List<Url> all = urlRepository.findAllUrlByName("jihuhwan");
		for (Url url: all) {
			log.info(url.getOriginurl());
		}
	}

	@Test
	public void find() {
		Optional<Url> findUrl = urlRepository.findUrl("jihuhwan", "https://naver.com");
		System.out.println("findUrl = " + findUrl.get().getHashvalue());
		Optional<Url> findUrls = urlRepository.findUrl("hwang", "https://naver.com");
		System.out.println("findUrls = " + findUrls.get().getHashvalue());
	}

	@Test
	public void getCreateInfoTest() throws ValidationException, NoSuchAlgorithmException, UnsupportedEncodingException {
		UrlResponseDto url = urlServiceImpl.createUrl("daum.net");
		log.info(url.getOriginUrl());
		log.info(url.getHashValue());
	}

	@Test
	public void Base62EncodingTest() {
		String encode = base62Converter.encoding("123456789a");
		log.info(encode);
	}

	@Test
	public void Base62DecodingTest() {
		String decode = base62Converter.decoding("abcdefg");
		log.info(decode);
	}

	@Test
	public void Sha512Test() throws NoSuchAlgorithmException {
		BigInteger bigInteger = new BigInteger("13");
		String res = sha512Converter.convert512(bigInteger.toString());
		log.info(res);
	}

	@Test
	public void UrlRepoTest() {
		boolean res = urlRepository.existsByhashvalue("3445678");
		log.info(String.valueOf(res));
	}

	@Test
	public void CreateV1Test() {
		String tenTest=  "c9b3d4812e";
		String originUrl = "https://naver.com";
		String name = "hwang";
		Url url = new Url(tenTest,originUrl,name);
		urlRepository.save(url);
	}

	@Test
	public void CreateV2Test() throws UnsupportedEncodingException {
		String naver  = "https://naver.com/login";
		String encode = URLEncoder.encode(naver, "UTF-8");
		log.info(encode);
	}


	@Test
	public void FindByChangeUrlTest() {
		Optional<Url> result = urlRepository.findByhashvalue("345678");
		log.info(result.get().getOriginurl());
		log.info(result.get().getHashvalue());
	}

	@Test
	public void exception() {
		String encoding = base62.encoding(BigInteger.valueOf(12345));
		log.info("encoding = {}", encoding);
	}
}
