package com.example.demo;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

public class FailSafe {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("One");
        list.add("Two");
        list.add("Three");

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            list.add("Four");
        }
    }
}

class FailFast_Iterator {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("One");
        list.add("Two");
        list.add("Three");

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            list.add("Four");
            System.out.println(list);
            System.out.println(iterator.next());
            /**
             * the ConcurrentModificationException is only thrown when the iterator's next() method is called,
             * as the iterator tracks modifications to the list.
             * from checkForComodification method of ArrayList class
             */
        }
    }
}

class FailFast_List_Map {
    static final Map<Integer, String> map = new HashMap<>();
    static final List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(map);
        System.out.println(list);
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (!entry.getValue().equals("four")) {
                /**
                 * The second time you call map.put(4, "four"),
                 * which invalidates the Iterator that is being used to iterate over the map.
                 * This change to the map's structure causes a mismatch in the modCount,
                 * leading to a ConcurrentModificationException.
                 */
                map.put(4, "four");
                list.add(5);
            }
        }
        System.out.println(map);
        System.out.println(list);
    }
}