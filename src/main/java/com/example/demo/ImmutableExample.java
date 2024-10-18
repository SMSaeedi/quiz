package com.example.demo;

import java.util.List;
import java.util.Map;

public class ImmutableExample {
    static final Map<Integer, String> map = Map.of(1, "one", 2, "two", 3, "three");
    static final List<Integer> list = List.of(1, 2, 3, 4);

    public static void main(String[] args) {
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (!entry.getValue().equals("four")) {
                /**
                 * Any operations (add(), remove()) causes UnsupportedOperationException from ImmutableCollections class
                 * */
                map.put(4, "four");
                list.add(5);
            }
        }
        System.out.println(map);
        System.out.println(list);
    }
}
