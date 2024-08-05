package com.example.demo.quiz.revolutquiz;

import java.util.HashMap;
import java.util.Map;

public class SecondStage {
    private final Map<String, Integer> map = new HashMap<>();

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof SecondStage && ((SecondStage) obj).map.equals(map);
    }
}