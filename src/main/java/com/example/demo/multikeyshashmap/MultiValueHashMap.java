package com.example.demo.multikeyshashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiValueHashMap<K, V> {
    private final HashMap<K, ArrayList<V>> map = new HashMap<>();

    public void put(K key, V value) {
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
    }

    public List<V> get(K key) {
        /**
         *map.get returns 'null' or a mapped value.
         *map.getOrDefault returns default key value (new ArrayList<>()) or the specific key value.
        */
        return map.getOrDefault(key, new ArrayList<>());
    }

    public void remove(K key, V value) {
        map.computeIfPresent(key, (k, v) -> {
            v.remove(value);
            return v;
        });
    }
}

class ReplacedDuplicatedValueHashMap<K, V> {
    static Map<Integer, String> map = new HashMap<>();

    public static void main(String[] args) {
        map.put(1, "one");
        map.put(2, "two");
        map.put(1, "first");
        System.out.println(map.get(1)); //returning 'first', 'one' is replaced by the second duplicated key
    }
}