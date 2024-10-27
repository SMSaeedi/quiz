package com.example.demo.acm;

public class Test4 {
    public static void main(String[] args) {
        String str = "BINGO";
        for (int i = 0; i < str.length(); i++) {
            str = str.replace(str.charAt(i), '*');
            System.out.println(str);
        }
    }
}
