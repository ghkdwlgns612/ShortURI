package com.example.demo;

import com.example.demo.domain.Url;
import com.example.demo.repository.UrlRepository;
import com.example.demo.utils.Base62Converter;
import com.example.demo.utils.Sha512Converter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
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
	public void Base62Test() {
		String encode = base62Converter.encoding("123456789a");
		System.out.println("encode = " + encode);
		String decode = base62Converter.decoding(encode);
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

	}

	@Test
	public void CreateV2Test() {

	}

	@Test
	public void FindByChangeUrlTest() {
		Optional<Url> result = urlRepository.findByhashvalue("345678");
		System.out.println("result.get().getOriginuri() = " + result.get().getOriginurl());
		System.out.println("result.get().getchangeduri() = " + result.get().getHashvalue());
	}
}
