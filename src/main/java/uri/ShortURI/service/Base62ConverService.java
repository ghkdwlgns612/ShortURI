package uri.ShortURI.service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Slf4j
public class Base62ConverService {
    final static String[] elements = {
            "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o",
            "p","q","r","s","t","u","v","w","x","y","z","1","2","3","4",
            "5","6","7","8","9","0","A","B","C","D","E","F","G","H","I",
            "J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X",
            "Y","Z"
    };

    private static final int default_plus = 3000000;

    private static final int base = 62;

    String toBase62(long base10){ //encoding
        StringBuilder base62 = new StringBuilder();
        base10 += default_plus;
        int mod = 0;
        while(base10 != 0) {
            mod = (int) base10 % base;
            base62.append(elements[mod]);
            base10 /= base;
        }
        return base62.reverse().toString();
    }
}
