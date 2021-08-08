package uri.ShortURI;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Base64Test {

    final static String[] elements = {
            "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o",
            "p","q","r","s","t","u","v","w","x","y","z","1","2","3","4",
            "5","6","7","8","9","0","A","B","C","D","E","F","G","H","I",
            "J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X",
            "Y","Z"
    };

    public static void main(String[] args) {
        String str = "abcd";
        int N = str.length();
        int cnt = 0;
        char[][] res = new char[N][8];
        for(int j=0;j<str.length();j++)
        {
            int a = str.charAt(j);
            for (int i = 128; i > 0 ; i /= 2)
            {
                if ((i & a) != 0)
                    res[j][cnt] = '1';
                else
                    res[j][cnt] = '0';
                cnt++;
            }
            cnt = 0;
        }

        for(int j=0;j<str.length();j++)
        {
            for (int i = 128; i > 0 ; i /= 2)
            {
                cnt++;
            }
        }
    }
}
