package com.example.demo;

public class RaceConditionTest {
    private int regularInt = 5;

    public int addAndGet(int val) {
        regularInt += val;
        return regularInt;
    }

    public int getAndAdd(int val) {
        int temp = regularInt;
        regularInt += val;
        return temp;
    }

    public int get() {
        return regularInt;
    }

    public static void main(String[] args) {
        RaceConditionTest test = new RaceConditionTest();

        new Thread(() -> System.out.println(test.addAndGet(10))).start();
        new Thread(() -> System.out.println(test.getAndAdd(5))).start();

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            System.out.println(test.get());
        }
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

class StringBuilderExample {
    public static void main(String[] args) {
        String str = "abc";
        StringBuilder strb = new StringBuilder(str);
        strb.reverse();
        System.out.println(str.equals(strb.toString()));
        str = strb.toString();
        System.out.println(str.equals(strb.toString()));
    }
}