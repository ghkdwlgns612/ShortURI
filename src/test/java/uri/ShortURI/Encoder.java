package uri.ShortURI;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Encoder {

    public static void main(String[] args) {
        String encode = URLEncoder.encode("https://naver.com/love", StandardCharsets.UTF_8);
        System.out.println("encode = " + encode);
    }
}
