package com.example.demo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StringExamples {
}

class SetStringArrayUnique {
    public static void main(String[] args) {
        int[] addresses = {1, 2, 3, 2, 1, 5, 3, 1, 2, 1, 4, 5, 6};
        int[] uniqueAddresses = uniqueIntArray(addresses);
        System.out.println(Arrays.toString(uniqueAddresses));   // Returns [1, 2, 3, 5, 4, 6]
    }

    private static int[] uniqueIntArray(int[] ads) {
        Set<Integer> set = new HashSet<>();
        for (int s : ads)
            set.add(s);

        int index = 0;
        int[] result = new int[set.size()];
        for (int s : set)
            result[index++] = s;

        return result;
    }
}

class FindDuplicatedInStringArray {
    public static void main(String[] args) {
        System.out.println(findFirstDuplicateID(new String[]{"X123", "A456", "X123", "B789", "A456", "C111"})); // Expected "X123"
        System.out.println(findFirstDuplicateID(new String[]{"Z999", "Y888", "Z999", "Y888"})); // Expected "Z999"
        System.out.println(findFirstDuplicateID(new String[]{"E100", "B200", "C300", "E100", "D400", "C300"})); // Expected "E100"
    }

    private static String findFirstDuplicateID(String[] strings) {
        Set<String> uniqueSet = new HashSet<>();
        Set<String> duplicatedSet = new HashSet<>();

        for (String s : strings)
            if (!uniqueSet.add(s)) {
                duplicatedSet.add(s);
                return s;
            }
        return "";
    }
}

class StringCount {
    public static void main(String[] args) {
        String str = "aabbbccaaa";
        System.out.println(encode(str));
    }

    public static String encode(String input) {
        if (input == null || input.isEmpty())
            return "";

        StringBuilder result = new StringBuilder();
        char currentChar = input.charAt(0);
        int count = 0;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == currentChar)
                count++;
            else {
                result.append(currentChar).append(count);
                currentChar = c;
                count = 1;
            }
        }
        result.append(currentChar).append(count);
        return result.toString();
    }
}

class StringReplace {
    public static void main(String[] args) {
        String str = "Java";
        //The replace method does not modify the original string
        // but instead returns a new string with the replacements made
        str.replace('a', 'o');
        System.out.println(str);
        System.out.println(str.replace('a', 'o'));
        System.out.println(str.replace("a", "o"));
        System.out.println(str.replaceAll("a", "o"));
    }
}

class StringReverse {
    public static void main(String[] args) {
        String str = "Reverse Me";
        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--)
            sb.append(str.charAt(i));
        System.out.println(sb);

        String str2 = "You Reversed Me";
        System.out.println(new StringBuilder(str2).reverse());

        String str3 = "You're about to Reverse Me";
        for (int i = str2.length() - 1; i >= 0; i--)
            System.out.print(str3.charAt(i));
    }
}