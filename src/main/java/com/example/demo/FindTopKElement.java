package com.example.demo;

import java.util.*;
import java.util.stream.Collectors;

public class FindTopKElement {
    public static void main(String[] args) {
        System.out.println(FindTopKElement.findTopKElementUsingTreeSet(List.of(1, 2, 3, 3, 4, 4, 5, 6), 2));
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