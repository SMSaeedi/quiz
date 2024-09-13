package com.example.demo;

public class StringReverse {
    public static void main(String[] args) {
        String str = "Reverse Me";
        StringBuffer sb = new StringBuffer();
        for (int i = str.length() - 1; i >= 0; i--)
            sb = sb.append(str.charAt(i));
        System.out.println(sb);

        String str2 = "You Reversed Me";
        System.out.println(new StringBuilder(str2).reverse());
    }
}
