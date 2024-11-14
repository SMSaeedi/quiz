package com.example.demo.onlinequiz;

import static java.lang.System.out;

public class Google {
    public static void main(String[] args) {
        String str = "bad";
        int result = 0;
        for (int i = 0; i < str.length() - 1; i++) // length - 1, for the last char no need to iterate
            result += Math.abs(str.charAt(i) - str.charAt(i + 1));

        out.println(str + " " + result);
    }
}