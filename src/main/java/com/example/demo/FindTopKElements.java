package com.example.demo;

import java.util.*;
import java.util.stream.Collectors;

public class FindTopKElements {
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
        var result = Arrays.stream(list)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .skip(1) //skip the first element to reach the second one
                .mapToInt(Integer::intValue)
                .findFirst();
        result.ifPresentOrElse(
                System.out::println,
                () -> {
                    throw new IllegalArgumentException("Arrays too small");
                });
    }
}

class FindMaxUsingBinarySearch{
    public static void main(String[] args) {
        int[] nums = new int[] {4, 3, 2, 1, 7, 6, 5};
        System.out.println(findMax(nums));  // Output: 7

        int[] nums2 = new int[] {3, 2, 1};
        System.out.println(findMax(nums2));  // Output: 3

        int[] nums3 = new int[] {6, 5, 4, 3, 2, 1, 7};
        System.out.println(findMax(nums3));  // Output: 7
    }

    public static int findMax(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] > nums[mid + 1])
                high = mid;
            else
                low = mid + 1;
        }
        return nums[low];
    }
}