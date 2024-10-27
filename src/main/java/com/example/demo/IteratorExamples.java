package com.example.demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.lang.System.out;

public class IteratorExamples {
}

class FailFastClass {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            out.println(iterator.next());
            list.add("C"); // This will cause a ConcurrentModificationException
        }
    }
}

class FailSafeClass {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("A");
        list.add("B");

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            out.println(iterator.next());
            list.add("C"); // This will not cause a ConcurrentModificationException
        }
    }
}