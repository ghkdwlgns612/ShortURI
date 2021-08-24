package com.example.demo.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

public class UtilsTest {

    private Sha512Converter sha512Converter = new Sha512Converter();
    private Base62Converter base62Converter = new Base62Converter();

    @Test
    void covert512Test() throws NoSuchAlgorithmException {
        String act = "c2453a5a9bcb484d58ee4479c242da98f08c2a1a77bafa234a6d35594b8a35690c0a6dcf633a7f3df6025fef570a8ef7d14ee2d5b3dddf600fd9bd7bcd2380a7";
        String tester = sha512Converter.convert512("https://naver.com");
        Assertions.assertThat(act).isEqualTo(tester);
    }

    @Test
    void encodingTest() {
        String act = "dlxZ5Ys";
        String encoding = base62Converter.encoding("2a1a77bafa");
        Assertions.assertThat(act).isEqualTo(encoding);
    }

    @Test
    void decodingTest() {
        String act = "2a1a77bafa";
        String decoding = base62Converter.decoding("dlxZ5Ys");
        Assertions.assertThat(act).isEqualTo(decoding);
    }
}
