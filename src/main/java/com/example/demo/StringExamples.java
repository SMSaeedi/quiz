package com.example.demo;

import java.util.*;

public class StringExamples {
}

class StringAnagram {
    public static void main(String[] args) {
        if (isAnagram("aab", "baa")) System.out.println("aab and baa are anagrams.");
        if (!isAnagram("aaa", "baa")) System.out.println("aaa and baa are not anagrams.");
        if (isAnagramUsingHashMap("listen", "silent")) System.out.println("listen and silent are anagrams.");
    }

    private static boolean isAnagramUsingHashMap(String str1, String str2) {
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        if (str1.length() != str2.length())
            return false;

        HashMap<Character, Integer> map1 = new HashMap<>();
        for (char c : str1.toCharArray())
            map1.put(c, map1.getOrDefault(c, 0) + 1);

        for (char c : str2.toCharArray()) {
            if (!map1.containsKey(c))
                return false;
            map1.put(c, map1.get(c) + 1);
            if (map1.get(c) == 1)
                map1.remove(c);
        }

        return true;
    }

    private static boolean isAnagram(String str1, String str2) {
        int count[] = new int[256];
        for (int i = 0; i < str1.length(); i++) {
            count[str1.charAt(i)]++;
            count[str2.charAt(i)]--;
        }

        for (int i = 0; i < count.length; i++)
            if (count[i] != 0)
                return false;

        return true;
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

class CharCount {
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
                result.append(count).append(currentChar).append(" ");
                currentChar = c;
                count = 1;
            }
        }
        result.append(count).append(currentChar).append(" ");
        return result.toString();
    }
}

class WordsCount {
    public static void main(String[] args) {
        String str1 = "Welcome to Java world!";
        System.out.println(str1.split(" ").length);
        System.out.println(Arrays.stream(str1.split(" ")).count());
    }
}

class Replace {
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

class CharReverse {
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

class StringWellFormed {
    public static void main(String[] args) {
        String strFormed = "[{()}]";
        String strNotFormed = "[{(}]";
        System.out.println(isStringFormed(strFormed));
        System.out.println(isStringFormed(strNotFormed));
    }

    private static boolean isStringFormed(String strFormed) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < strFormed.length(); i++) {
            char c = strFormed.charAt(i);
            if (stack.isEmpty()) //This is for the very first time, which the stack is empty
                stack.push(c);
            else if (c == '[' || c == '{' || c == '(')
                stack.push(c);
            else if (c == ']' && stack.peek() == '[')
                stack.pop();
            else if (c == '}' && stack.peek() == '{')
                stack.pop();
            else if (c == ')' && stack.peek() == '(')
                stack.pop();
        }
        return stack.isEmpty();
    }
}

class PrintLength {
    public static void main(String[] args) {
        String srt = "Hi World";
        for (String s : srt.split(" "))
            if (s.length() % 2 == 0) //if the length is Even then print it
                System.out.println(s);
    }
}

class SunInteger {
    public static void main(String[] args) {
        String str1 = "554";
        String str2 = "896";
        int sum = Integer.parseInt(str1) + Integer.parseInt(str2);
        System.out.println(sum);
    }
}

class WordsReverse {
    public static void main(String[] args) {
        String str1 = "Hello Java, Welcome to our world!";
        StringBuilder sb = new StringBuilder();
        String[] s1 = str1.split(" ");
        for (int i = s1.length - 1; i >= 0; i--)
            sb.append(s1[i]).append(" ");
        System.out.println(sb);
    }
}

class DuplicatedCharCount {
    public static void main(String[] args) {
        String str = "Mmahhssa"; // to ignore case .toLowerCase()
        HashMap<Character, Integer> duplicatedMap = new HashMap();
        HashMap<Character, Integer> uniqueMap = new HashMap();
        HashMap<Character, Integer> map = new HashMap();
        for (char c : str.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);

        for (Map.Entry<Character, Integer> entry : map.entrySet())
            if (entry.getValue() != 1)
                duplicatedMap.put(entry.getKey(), entry.getValue());
            else uniqueMap.put(entry.getKey(), uniqueMap.getOrDefault(entry.getKey(), 0) + 1);

        System.out.println("Map: " + map);
        System.out.println("Duplicated Map: " + duplicatedMap);
        System.out.println("Unique Map: " + uniqueMap);
    }
}

class WordsAndCharsCount {
    public static void main(String[] args) {
        String str = "This is a Java Program";
        System.out.println(str);
        System.out.println("Nr of words: " + str.split(" ").length);
        int charCount = 0;
        for (String c : str.split(" "))
            charCount += c.length();
        System.out.println("Nr of chars without spaces: " + charCount);
        System.out.println("Nr of chars with spaces: " + str.length());
    }
}

class FindFirstUniqueChar {
    static String str = "efficient";
    static String str1 = "mahsa";
    static String str2 = "ssff";
    static String str3 = "";

    public static void main(String[] args) {
        System.out.println(findFirstUniqueCharacter(str));
        System.out.println(findFirstUniqueCharacter(str2));
        System.out.println(findFirstUniqueCharacter(str1));
        System.out.println(findFirstUniqueCharacter(str3));
    }

    private static Character findFirstUniqueCharacter(String input) {
        char outPut;

        if (input.isEmpty())
            return null;

        for (char ch : input.toCharArray())
            if (input.indexOf(ch) == input.lastIndexOf(ch)) {
                outPut = ch;
                return outPut;
            }

        return null;
    }
}