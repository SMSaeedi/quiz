package com.example.demo.onlinequiz;

import static java.lang.System.out;

public class Google {
    public static void main(String[] args) {
        String str = "bad";
        findCharDistance(str);
        findCharDistanceUsingToCharArray(str);
    }

    private static void findCharDistance(String str) {
        int result = 0;
        for (int i = 1; i < str.length(); i++) // length - 1, for the last char no need to iterate
            result += Math.abs(str.charAt(i) - str.charAt(i - 1));

        out.println(str + " " + result);
    }

    private static void findCharDistanceUsingToCharArray(String str) {
        int result = 0;
        for (int i = 0; i < str.toCharArray().length - 1; i++)
            result += Math.abs(str.toCharArray()[i] - str.toCharArray()[i + 1]);

        out.println(str + " " + result);
    }
}