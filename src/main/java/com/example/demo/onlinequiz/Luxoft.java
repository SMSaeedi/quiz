package com.example.demo.onlinequiz;

import java.util.Arrays;

import static java.lang.System.out;

public class Luxoft {
    // LargestNumber At Least Twice Of Others
    public static void main(String[] args) {
        int[] arr1 = {3, 2};
        out.println(largestNumberTwiceSize(arr1));
        int[] arr2 = {3, 2, 1};
        out.println(largestNumberTwiceSize(arr2));
    }

    static int largestNumberTwiceSize(int[] arr) {
        Arrays.sort(arr); // O(nLogn)
        int max = arr[arr.length - 1];
        int counter = 0;
        for (int i : arr) // O(n)
            if (i * i <= max)
                counter++;
        return counter > 0 ? max : -1;
    }
}
