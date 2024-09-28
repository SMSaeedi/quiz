package com.example.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfaceExamples {
    public static void main(String[] args) {
        Function<String, Integer> stringLengthFunction = str -> str.length();
        String input = "Hello, World!";
        Integer length = stringLengthFunction.apply(input);
        System.out.println("The length of the string '" + input + "' is: " + length);

        Consumer<String> printUppercaseConsumer = str -> System.out.println("Uppercase: " + str.toUpperCase());
        printUppercaseConsumer.accept("hello");

        Supplier<String> messageSupplier = () -> "This is the default message";
        String message = messageSupplier.get();
        System.out.println("Supplier provided: " + message);

        Map<String, Integer> map1 = new HashMap<>();
        map1.put("Peach", 10);
        map1.put("Apple", 5);
        map1.put("PeachPie", 15);
        Predicate<Map.Entry<String, Integer>> predicate = e -> e.getKey().startsWith("Peach");
        for (Map.Entry<String, Integer> entry : map1.entrySet())
            if (predicate.test(entry))
                System.out.println("Matched Entry: " + entry.getKey() + " -> " + entry.getValue());
    }
}