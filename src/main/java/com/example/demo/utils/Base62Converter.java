package com.example.demo.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

public class Base62Converter {
    final static String[] base62 = {
            "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o",
            "p","q","r","s","t","u","v","w","x","y","z","1","2","3","4",
            "5","6","7","8","9","0","A","B","C","D","E","F","G","H","I",
            "J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X",
            "Y","Z"
    };

    public String encoding(String extract10Char) {
        BigInteger base10 = new BigInteger(extract10Char,16);
        StringBuilder result = new StringBuilder();
        while (base10 != BigInteger.valueOf(0)) {
            BigInteger tmp = get62BaseString(base10);
            result.append(base62[Integer.parseInt(tmp.toString())]);
            base10 = base10.divide(BigInteger.valueOf(62));
        }
        return result.reverse().toString();
    }

    public String decoding(String encodedStr) {
        double result = 0.0;
        for(int i = 0;i < encodedStr.length();i++) {
            double res = get10BaseNumber(encodedStr, i);
            result += res;
        }
        BigInteger bigInteger = BigDecimal.valueOf(result).toBigInteger();
        return bigInteger.toString(16);
    }

    private BigInteger get62BaseString(BigInteger base10) {
        BigInteger tmp = new BigInteger(String.valueOf(base10.mod(BigInteger.valueOf(62))));
        return tmp;
    }

    private double get10BaseNumber(String encodedStr, int i) {
        return Arrays.asList(base62).
                indexOf(encodedStr.substring(i, i + 1)) * Math.pow(62.0, (double) (encodedStr.length() - 1 - i));
    }
}
