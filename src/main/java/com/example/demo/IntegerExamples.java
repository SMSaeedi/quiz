package com.example.demo;

import java.util.*;
import java.util.stream.Collectors;

public class IntegerExamples {
}

class DuplicatedIntArray {
    public static void main(String[] args) {
        int[] addresses = {1, 2, 3, 2, 1, 5, 3, 1, 2, 1, 4, 5, 6};
        int[] uniqueAddresses = findDuplicates(addresses);
        System.out.println(Arrays.toString(uniqueAddresses));   // Returns [1, 2, 3, 5, 4, 6]
    }

    private static int[] findDuplicates(int[] ads) {
        Set<Integer> set = new HashSet<>();
        for (int s : ads)
            set.add(s);
        System.out.println(set);

        int i = 0;
        int[] result = new int[set.size()];
        for (int s : set)
            result[i++] = s;

        return result;
    }
}

class FindTopKElements {
    public static void main(String[] args) {
        System.out.println(FindTopKElements.findTopKElementUsingTreeSet(List.of(1, 2, 3, 3, 4, 4, 5, 6), 2));
    }

    public static List<Integer> findTopKElementUsingTreeSet(List<Integer> lst, int k) {
        Set<Integer> sortedSet = new TreeSet<>(Comparator.reverseOrder());
        sortedSet.addAll(lst);
        return sortedSet.stream().limit(k).collect(Collectors.toList());
    }
}

class FindSecondTopElement {
    public static void main(String[] args) {
        int[] list = {5, 3, 9, 7, 2, 8};
        var result = Arrays.stream(list).boxed().sorted(Comparator.reverseOrder()).skip(1) //skip the first element to reach the second one
                .mapToInt(Integer::intValue)// This line can be ignored
                .findFirst();
        result.ifPresentOrElse(System.out::println, () -> {
            throw new IllegalArgumentException("Arrays too small");
        });
    }
}

class FindMaxUsingBinarySearch {
    public static void main(String[] args) {
        int[] nums = new int[]{4, 3, 2, 1, 7, 6, 5};
        System.out.println(findMax(nums));  // Output: 7

        int[] nums2 = new int[]{3, 2, 1};
        System.out.println(findMax(nums2));  // Output: 3

        int[] nums3 = new int[]{6, 5, 4, 3, 2, 1, 7};
        System.out.println(findMax(nums3));  // Output: 7
    }

    public static int findMax(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] > nums[mid + 1]) high = mid;
            else low = mid + 1;
        }
        return nums[low];
    }
}

class findUniqueInt {
    public static int findUniqueInteger(int[] arr) {
        int unique = 0;
        for (int num : arr)
            unique ^= num;

        return unique;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 3, 2, 4, 1, 3, 2};
        System.out.println("The unique integer is: " + findUniqueInteger(arr));  // Output should be 1
    }
}

class FindUniqueIntArray {
    public static int[] findUniqueIntegerArray(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr)
            map.put(num, map.getOrDefault(num, 0) + 1);

        List<Integer> uniqueList = new ArrayList<>();
        for (Integer num : arr)
            if (map.get(num) == 1) uniqueList.add(num);

        int[] result = new int[uniqueList.size()];
        int i = 0;
        for (Integer num : uniqueList)
            result[i++] = num;

        return result;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{4, 3, 2, 4, 1, 3, 2, 5, 7, 7, 9, 8, 11};
        System.out.println("The unique integers are: " + Arrays.toString(findUniqueIntegerArray(arr1)));  // Output should be 1
    }
}

class NumberContiguousList {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 5, 3, 4, 2);
        System.out.println(isNumberContiguous(numbers) ? "Yes" : "No");

        List<Integer> numbers1 = List.of(1, 3, 5, 4, 6);
        System.out.println(isNumberContiguous(numbers1) ? "Yes" : "No");
    }

    private static boolean isNumberContiguous(List<Integer> numbers) {
        if (numbers.isEmpty())
            return false;

        var sortedList = numbers.stream().sorted(Comparator.naturalOrder()).toList();

        for (int i = 1; i < sortedList.size() - 1; i++)
            if (sortedList.get(i) - sortedList.get(i - 1) != 1)
                return false;

        return true;
    }

}

class NumberContiguousArray {
    public static void main(String[] args) {
        int[] arr1 = {2, 6, 3, 8, 1, 9, 11, 12};
        System.out.println(isNumberContinueSequentially(arr1));
        int[] arr2 = {2, 6, 3, 8, 1, 7, 4, 5};
        System.out.println(isNumberContinueSequentially(arr2));
    }

    private static boolean isNumberContinueSequentially(int[] arr1) {
        if (arr1 == null || arr1.length == 0)
            return false;

        Arrays.sort(arr1);

        for (int i = 1; i < arr1.length - 1; i++)
            if (arr1[i] - arr1[i - 1] != 1)
                return false;

        return true;
    }
}

class AverageTwoArrays {
    public static void main(String[] args) {
        int[][] arr = {{2, 6, 3, 8, 1, 9, 11, 12}, {0, 3, 2, 1, 10, 5}};
        int sum = 0;
        double average = 0;
        int totalElements = 0;

        for (int i = 0; i < arr.length; i++)
            for (int j = 0; j < arr[i].length; j++) {
                sum += arr[i][j];
                totalElements++;
            }

        average = sum / totalElements;
        System.out.println("Sum " + sum);
        System.out.println("Total Elements " + totalElements);
        System.out.println("Average " + average);
    }
}