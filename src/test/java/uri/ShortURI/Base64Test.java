package uri.ShortURI;

import org.junit.jupiter.api.Test;
import uri.ShortURI.utils.Base62ConverService;
import uri.ShortURI.utils.Sha512Converter;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Base64Test {
    private Base62ConverService base62ConverService = new Base62ConverService();
    private Sha512Converter sha512Converter = new Sha512Converter();

    @Test
    public void Base62Test() throws NoSuchAlgorithmException {
        String a = sha512Converter.convert512("a");
        a = base62ConverService.changeBase62(a);
        System.out.println("a = " + a);
    }
}
