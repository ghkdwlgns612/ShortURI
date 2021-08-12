package com.example.demo;

import com.example.demo.domain.Url;
import com.example.demo.repository.UrlRepository;
import com.example.demo.utils.Base62Converter;
import com.example.demo.utils.Sha512Converter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;


@SpringBootTest
class ShortenApplicationTests {

	@Autowired
	private UrlRepository urlRepository;

	private Sha512Converter sha512Converter = new Sha512Converter();
	private Base62Converter base62Converter = new Base62Converter();

	@Test
	public void getId() {
		Url url = new Url("asdmsa","ASdoajdas");
		System.out.println(url.getId());
	}

	@Test
	public void Base62EncodingTest() {
		String encode = base62Converter.encoding("123456789a");
		System.out.println("encode = " + encode);
	}

	@Test
	public void Base62DecodingTest() {
		String decode = base62Converter.decoding("abcdefg");
		System.out.println("decode = " + decode);
	}

	@Test
	public void Sha512Test() throws NoSuchAlgorithmException {
		BigInteger bigInteger = new BigInteger("12");
		String res = sha512Converter.convert512(bigInteger.toString());
		System.out.println("res = " + res);
	}

	@Test
	public void UrlRepoTest() {
		boolean res = urlRepository.existsByhashvalue("3445678");
		System.out.println("res = " + res);
	}

	@Test
	public void CreateV1Test() {
		String tenTest=  "c9b3d4812e";
		String originUrl = "https://naver.com";
		Url url = new Url(tenTest,originUrl);
		urlRepository.save(url);
	}

	@Test
	public void CreateV2Test() throws UnsupportedEncodingException {
		String naver  = "https://naver.com/login";
		String encode = URLEncoder.encode(naver, "UTF-8");
		System.out.println("encode = " + encode);
	}


	@Test
	public void FindByChangeUrlTest() {
		Optional<Url> result = urlRepository.findByhashvalue("345678");
		System.out.println("result.get().getOriginuri() = " + result.get().getOriginurl());
		System.out.println("result.get().getchangeduri() = " + result.get().getHashvalue());
	}
}
