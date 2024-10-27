package com.example.demo.onlinequiz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;

class EventQuiz {
    public static void main(String[] args) {
        Map<String, Long> map = new HashMap<>();
        List<String> keySequence = new ArrayList<>();

        checkDuplicateInMap(map, keySequence, "foo", 1000001L);
        checkDuplicateInMap(map, keySequence, "bar", 1000002L);
        checkDuplicateInMap(map, keySequence, "baz", 1000005L);
        checkDuplicateInMap(map, keySequence, "foo", 1000006L);
        checkDuplicateInMap(map, keySequence, "bar", 1000014L);
        checkDuplicateInMap(map, keySequence, "baz", 1000017L);
        checkDuplicateInMap(map, keySequence, "foo", 1000020L);

        out.println(String.join(", ", keySequence));
    }

    private static void checkDuplicateInMap(Map<String, Long> map, List<String> keySequence, String key, Long value) {
        if (map.containsKey(key)) {
            Long previousValue = map.get(key);
            if (Math.abs(value - previousValue) >= 10)
                keySequence.add(key);
        } else
            keySequence.add(key);

        map.put(key, value);
    }
}