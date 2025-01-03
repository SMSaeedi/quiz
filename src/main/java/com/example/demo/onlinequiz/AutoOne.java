package com.example.demo.onlinequiz;

import java.util.HashMap;
import java.util.Map;

public class AutoOne {
    public Map<String, String> map = new HashMap<>();

    public String getKey(String key, String secret) {
        if (key.length() > 20)
            throw new RuntimeException("Invalid length!");

        map.put(key.toLowerCase(), secret);

        return key;
    }

    public String getSecret(String key) {
        return map.get(key.toLowerCase());
    }
}