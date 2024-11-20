package com.example.demo;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.out;

class CommonChars {
    public static void main(String[] args) {
        out.println(commonChild("WEWOUCUIDGCGTRMEZEPXZFEJWISRSBBSYXAYDFEJJDLEBVHHKS", "FDAGCXGKCTKWNECHMRXZWMLRYUCOCZHJRRJBOAJOQJZZVUYXIC"));
        out.println(commonChild("Mahsa", "Sara"));
        out.println(commonChild("Mahsa", "Jonnei"));
        out.println(commonChild("Setareh", "setar0h0"));
    }

    static int commonChild(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) //O(n)
            for (int j = 1; j <= n; j++) { //O(n)
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);

            }

        return dp[m][n];
    }
}

class StringAnagram {
    public static void main(String[] args) {
        if (isAnagram1("aab", "baa")) out.println("aab and baa are anagrams.");
        if (!isAnagram1("aaa", "baa")) out.println("aaa and baa are not anagrams.");
        if (isAnagramHashMap("listen", "silent")) out.println("listen and silent are anagrams.");
    }

    static boolean isAnagramHashMap(String str1, String str2) {
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        if (str1.length() != str2.length())
            return false;

        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : str1.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);

        for (char c : str2.toCharArray()) {
            if (!map.containsKey(c))
                return false;
            map.put(c, map.get(c) + 1);
            if (map.get(c) == 1)
                map.remove(c);
        }

        return true;
    }

    static boolean isAnagram1(String str1, String str2) {
        int[] count = new int[128];
        for (int i = 0; i < str1.length(); i++) {
            count[str1.charAt(i)]++;
            count[str2.charAt(i)]--;
        }

        for (int j : count)
            if (j != 0)
                return false;

        return true;
    }
}

class FindDuplicatedInStringArray {
    public static void main(String[] args) {
        out.println(findFirstDuplicateID(new String[]{"X123", "A456", "X123", "B789", "A456", "C111"})); // Expected "X123"
        out.println(findFirstDuplicateID(new String[]{"Z999", "Y888", "Z999", "Y888"})); // Expected "Z999"
        out.println(findFirstDuplicateID(new String[]{"E100", "B200", "C300", "E100", "D400", "C300"})); // Expected "E100"

        out.println(findDuplicateIDs(new String[]{"X123", "A456", "X123", "B789", "A456", "C111"}));
        out.println(findDuplicateIDs(new String[]{"Z999", "Y888", "Z999", "Y888"}));
        out.println(findDuplicateIDs(new String[]{"E100", "B200", "C300", "E100", "D400", "C300"}));
    }

    static String findFirstDuplicateID(String[] strings) {
        Set<String> uniqueSet = new HashSet<>();

        for (String s : strings)
            if (!uniqueSet.add(s))
                return s;

        return "";
    }

    static List<String> findDuplicateIDs(String[] strings) {
        Set<String> uniqueSet = new HashSet<>();
        List<String> duplicatedElements = new ArrayList<>();

        for (String s : strings)
            if (!uniqueSet.add(s))
                duplicatedElements.add(s);

        return duplicatedElements;
    }
}

class CharCount {
    public static void main(String[] args) {
        out.println(charCount1("aabbbccaaa"));
        out.println(charCount2("aabbbccaaa"));
    }

    static String charCount1(String input) {
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

    static String charCount2(String input) {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        for (String s : input.split(""))
            map.put(s, map.getOrDefault(s, 0) + 1);

        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Integer> entry : map.entrySet())
            result.append(entry.getValue()).append(entry.getKey()).append(" ");

        return result.toString();
    }
}

class StringLength {
    public static void main(String[] args) {
        String str1 = "Welcome to Java world!";
        out.println(str1.split(" ").length);
        out.println(Arrays.stream(str1.split(" ")).count());
    }
}

class WordCounter {
    public static Map<String, Integer> countWords(String text) {
        Map<String, Integer> wordCount = new HashMap<>();

        for (String word : text.toLowerCase().split(""))
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);

        return wordCount;
    }

    public static void main(String[] args) {
        System.out.println(countWords("Mahsa"));
        System.out.println(Arrays.stream("Mahsa".split(" ")).count());
    }
}

class StringReplace {
    public static void main(String[] args) {
        String str = "Java";
        // replace() does not modify the original string, it returns a new string
        str.replace('a', 'o');
        out.println(str);
        out.println(str.replace('a', 'o'));
        out.println(str.replace("a", "o"));
        out.println(str.replaceAll("a", "o"));
    }
}

class CharReverse {
    public static void main(String[] args) {
        String str = "Reverse Me";
        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--)
            sb.append(str.charAt(i));
        out.println(sb);

        String str2 = "You Reversed Me";
        out.println(new StringBuilder(str2).reverse());

        String str3 = "You're about to Reverse Me";
        for (int i = str2.length() - 1; i >= 0; i--)
            out.print(str3.charAt(i));
    }
}

class WellFormedString {
    public static void main(String[] args) {
        String strFormed = "[{()}]";
        String strNotFormed = "[{(}]";
        out.println(isStringFormed(strFormed));
        out.println(isStringFormed(strNotFormed));
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
                out.println(s);
    }
}

class SunInteger {
    public static void main(String[] args) {
        String str1 = "554";
        String str2 = "896";
        int sum = Integer.parseInt(str1) + Integer.parseInt(str2);
        out.println(sum);
    }
}

class WordsReverse {
    public static void main(String[] args) {
        String str1 = "Hello Java, Welcome to our world!";
        StringBuilder sb = new StringBuilder();
        String[] s1 = str1.split(" ");
        for (int i = s1.length - 1; i >= 0; i--)
            sb.append(s1[i]).append(" ");
        out.println(sb);
    }
}

class DuplicatedCharCount {
    public static void main(String[] args) {
        String str = "Mmahhssa"; // to ignore case .toLowerCase()
        HashMap<Character, Integer> duplicatedMap = new HashMap<>();
        HashMap<Character, Integer> uniqueMap = new HashMap<>();
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : str.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);

        for (Map.Entry<Character, Integer> entry : map.entrySet())
            if (entry.getValue() != 1)
                duplicatedMap.put(entry.getKey(), entry.getValue());
            else uniqueMap.put(entry.getKey(), uniqueMap.getOrDefault(entry.getKey(), 0) + 1);

        out.println("Map: " + map);
        out.println("Duplicated Map: " + duplicatedMap);
        out.println("Unique Map: " + uniqueMap);
    }
}

class WordsAndCharsCount {
    public static void main(String[] args) {
        String str = "This is a Java Program";
        out.println(str);
        out.println("Nr of words: " + str.split(" ").length);
        int charCount = 0;
        for (String c : str.split(" "))
            charCount += c.length();
        out.println("Nr of chars without spaces: " + charCount);
        out.println("Nr of chars with spaces: " + str.length());
    }
}

class FindFirstUniqueChar {
    public static void main(String[] args) {
        out.println(findFirstUniqueCharacter("efficient"));
        out.println(findFirstUniqueCharacter("mahsa"));
        out.println(findFirstUniqueCharacter("ssff"));
        out.println(findFirstUniqueCharacter(""));

        out.println(findFirstUniqueCharUsingMap("efficient"));
        out.println(findFirstUniqueCharUsingMap("mahsa"));
        out.println(findFirstUniqueCharUsingMap("ssff"));
        out.println(findFirstUniqueCharUsingMap(""));

        out.println(findFirstUniqueCharUsingStreamAPI("efficient"));
        out.println(findFirstUniqueCharUsingStreamAPI("mahsa"));
        out.println(findFirstUniqueCharUsingStreamAPI("ssff"));
        out.println(findFirstUniqueCharUsingStreamAPI(""));
    }

    static Character findFirstUniqueCharacter(String input) {
        if (input.isEmpty())
            return null;

        for (char ch : input.toCharArray())
            if (input.indexOf(ch) == input.lastIndexOf(ch))
                return ch;

        return null;
    }

    static Character findFirstUniqueCharUsingMap(String input) {
        Map<String, Integer> map = new HashMap<>();
        for (String ch : input.split(""))
            map.put(ch, map.getOrDefault(ch, 0) + 1);

        for (Map.Entry<String, Integer> entry : map.entrySet()) // 𝑂(𝑘), where 𝑘 is nr of map's entries (size of the map)
            if (entry.getValue() == 1 && !entry.getKey().isEmpty())
                return entry.getKey().charAt(0);

        return null;
    }

    static Character findFirstUniqueCharUsingStreamAPI(String input) {
        return input.chars()
                .mapToObj(c -> (char) c) // Convert int stream to character stream
                .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting())) // Group and count
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 1) // Filter entries with count == 1
                .map(Map.Entry::getKey) // Extract the character
                .findFirst().orElse(null); // Get the first one
    }
}

class FindSecondUniqueChar {
    public static void main(String[] args) {
        out.println(findSecondUniqueCharacter("efficient"));
        out.println(findSecondUniqueCharacter("mahsa"));
        out.println(findSecondUniqueCharacter("ssff"));
        out.println(findSecondUniqueCharacter(""));
        out.println(findSecondUniqueCharacter("raja"));
        out.println(findSecondUniqueCharacter("test"));
        out.println("-------------------------------------");
        out.println(findSecondUniqueChar("efficient"));
        out.println(findSecondUniqueChar("mahsa"));
        out.println(findSecondUniqueChar("ssff"));
        out.println(findSecondUniqueChar(""));
        out.println(findSecondUniqueChar("raja"));
        out.println(findSecondUniqueChar("test"));
    }

    static Character findSecondUniqueCharacter(String input) {
        /*
         *  Use LinkedHashMap to preserve insertion order --> {e=2, f=2, i=2, c=1, n=1, t=1}
         *  HashMap demonstrate --> {c=1, t=1, e=2, f=2, i=2, n=1}
         */
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (char ch : input.toCharArray())
            map.put(ch, map.getOrDefault(ch, 0) + 1);

        out.println(map);
        int charCount = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet())
            if (entry.getValue() == 1) {
                charCount++;
                if (charCount == 2)
                    return entry.getKey();
            }

        return null;
    }

    static Character findSecondUniqueChar(String input) {
        char outPut;
        int count = 0;

        if (input.isEmpty())
            return null;

        for (char ch : input.toCharArray())
            if (input.indexOf(ch) == input.lastIndexOf(ch)) {
                count++;
                if (count == 2) {
                    outPut = ch;
                    return outPut;
                }
            }

        return null;
    }
}

class StringContiguous {
    public static void main(String[] args) {
        String str1 = "ABDGFG";
        out.println(isStringContiguous(str1));
        String str2 = "ABCD";
        out.println(isStringContiguous(str2));
    }

    static boolean isStringContiguous(String str) {
        if (str == null || str.isEmpty())
            return false;

        for (int i = 1; i < str.length(); i++)
            if (str.charAt(i) - str.charAt(i - 1) != 1)
                return false;

        return true;
    }
}

class StringPalindrome {
    public static void main(String[] args) {
        String str1 = "abcdcba";
        String str2 = "Madam";
        String str3 = "mahsaa";

        out.println(isPalindrome(str1));
        out.println(isPalindrome(str2));
        out.println(isPalindrome(str3));
    }

    static boolean isPalindrome(String str) {
        return new StringBuilder(str).reverse().toString().equalsIgnoreCase(str);
    }
}

class StarCase {
    public static void main(String[] args) {
        int n = 10;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n - i; j++)
                out.print(" ");

            for (int m = 1; m <= i; m++)
                out.print("*");

            out.println();
        }
    }
}