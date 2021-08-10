package uri.ShortURI.utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
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

    public String changeBase62(String base16) {
        base16 = base16.substring(20,31);
        log.info("Cut str {}",base16);
        String str = new BigInteger(base16, 16).toString(2);
        log.info("Binary str {}",str);
        int cnt = 0;
        String res = "";
        for (int i=0;i<7;i++)
        {
            int index = 0;
            for (int j=32;j>0;j/=2)
            {
                index += (j * Integer.parseInt(String.valueOf(str.charAt(cnt++))));
            }
            res += elements[index].toString();
        }
        return res;
    }
}
