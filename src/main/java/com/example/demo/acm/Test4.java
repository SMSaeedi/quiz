package com.example.demo.acm;

import static java.lang.System.out;

public class Test4 {
   public static void main(String[] args) {
        String str = "BINGO";
        for (int i = 0; i < str.length(); i++) {
            str = str.replace(str.charAt(i), '*');
            out.println(str);
        }
    }
}
