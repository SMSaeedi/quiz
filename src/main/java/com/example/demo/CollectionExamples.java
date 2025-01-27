package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class CollectionExamples {
}

class SortListTest {
    private static final List<LocalDate> dates = List.of(
            LocalDate.of(2024, 5, 11)
            , LocalDate.of(2024, 5, 16)
            , LocalDate.of(2024, 5, 10)
            , LocalDate.of(2024, 6, 1));

    public static void main(String[] args) {
        out.println(dates.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()));
        out.println(dates.stream().sorted().collect(Collectors.toList()));
        out.println(dates.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList()));
    }

}

class SortMapTest {
    public static void main(String[] args) {
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("Bnanna", 1);
        map1.put("Apple", 2);
        map1.put("Orange", 3);
        map1.put("Peach", 4);
        TreeMap<String, Integer> asc = new TreeMap<>(map1);
        out.println(asc);
        TreeMap<String, Integer> desc = new TreeMap<>(Comparator.reverseOrder());
        desc.putAll(map1);
        out.println(desc);

        Map<Integer, String> map2 = new HashMap<>();
        map2.put(5, "Bnanna");
        map2.put(2, "Apple");
        map2.put(1, "Orange");
        map2.put(4, "Peach");
        map2.put(3, "Melon");
        TreeMap<Integer, String> asc2 = new TreeMap<>(map2);
        out.println(asc2);
        TreeMap<Integer, String> desc2 = new TreeMap<>(Comparator.reverseOrder());
        desc2.putAll(map2);
        out.println(desc2);
    }
}

class FindMax {
    public static void main(String[] args) {
        List<Employee> employeeList = List.of(
                new Employee("Alice", 30),
                new Employee("David", 50),
                new Employee("Bob", 45),
                new Employee("Charlie", 25)
        );

        out.println(employeeList.stream()
                .max(Comparator.comparing(Employee::getAge))
                .orElse(new Employee()));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Employee {
        String name;
        int age;
    }
}