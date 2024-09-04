package com.example.demo;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortListTests {
    private static List<LocalDate> dates = List.of(
            LocalDate.of(2024, 5, 11)
            , LocalDate.of(2024, 5, 16)
            , LocalDate.of(2024, 5, 10)
            , LocalDate.of(2024, 6, 1));

    public static void main(String[] args) {
        System.out.println(dates.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()));
        System.out.println(dates.stream().sorted().collect(Collectors.toList()));
        System.out.println(dates.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList()));
    }

}