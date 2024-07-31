package com.example.demo.revolutquiz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SecondStageTest {
    private Map<String, ArrayList<Integer>> map;
    private ArrayList<Integer> valueList;
    private ConcurrentHashMap<String, Integer> concurrentMap;

    @BeforeEach
    void setUp() {
        map = new HashMap<>();
        valueList = new ArrayList<>();
        concurrentMap = new ConcurrentHashMap<>();

        valueList.add(1);
        map.put("one", valueList);

        concurrentMap.put("one", 1);
        concurrentMap.put("two", 2);
        concurrentMap.put("three", 3);
    }

    @Test
    void map_test() {
        valueList.add(11);
        map.put("one", valueList);
        assertEquals(map.get("one"),List.of(1, 11));
    }

    @Test
    void concurrent_map_test() {
        concurrentMap.put("one", 11);
        assertNotEquals(concurrentMap.get("one"), 1);
        assertNotEquals(concurrentMap.get("one"), 11);
    }
}