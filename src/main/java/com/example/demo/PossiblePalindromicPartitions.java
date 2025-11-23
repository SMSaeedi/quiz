package com.example.demo;

public class PossiblePalindromicPartitions {
    private static List<List<String>> partition(String s){
        List<List<String>> result=new ArrayList<>();
        track(s, 0, new ArrayList<>(), result);
        return result;
    }
    
    private static void track(String s, int start, List<String> current, List<List<String>> result){
        if(start == s.length()){
            result.add(new ArrayList<>(current));
            return;
        }
        
        for (int end=start; end<s.length();end++){
            if(isPalindrome(s, start,end)){
                current.add(s.substring(start, end + 1));
                track(s,end+1, current, result);
                current.remove(current.size()-1);   
            }
        }
    }
    
    private static boolean isPalindrome(String s, int left, int right){
        while(left<right){
            if(s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }
  
    public static void main(String[] args){
        String s1 = "aab";
        String s2 = "aaba";
        String s3 = "abba";

        System.out.println("Partitions for " + s1 + ":");
        System.out.println(partition(s1));

        System.out.println("Partitions for " + s2 + ":");
        System.out.println(partition(s2));

        System.out.println("Partitions for " + s3 + ":");
        System.out.println(partition(s3));
     
    }

}