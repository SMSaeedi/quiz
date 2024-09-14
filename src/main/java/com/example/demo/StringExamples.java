package com.example.demo;

public class StringExamples {
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
        for(int i = str2.length() - 1; i >= 0; i--)
            System.out.print(str3.charAt(i));
    }
}