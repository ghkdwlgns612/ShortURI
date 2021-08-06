package uri.ShortURI;

import java.nio.charset.StandardCharsets;

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
        for(int j=0;j<str.length();j++)
        {
            int a = str.charAt(j);
            String res = "";
            for (int i = 1; i <129 ; i *= 2) {
                if ((a % i) == 1)
                    System.out.print("1");
                else
                    System.out.print("0");
            }
            System.out.println("");
        }
    }
}
