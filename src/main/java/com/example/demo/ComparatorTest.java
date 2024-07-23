package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorTest {
    public static void main(String[] args) {
        List<Studentt> studentList = new ArrayList<>();
        studentList.add(new Studentt("Mahsa", 30));
        studentList.add(new Studentt("Sara", 38));
        studentList.add(new Studentt("Mahshid", 29));
        studentList.add(new Studentt("Dara", 27));

        Studentt student = new Studentt();
        Collections.sort(studentList, student);
        System.out.println(studentList);
    }
}

class Studentt implements Comparator<Studentt> {
    String name;
    int age;

    public Studentt() {
    }

    public Studentt(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return "Student{name: " + name + ", age: " + age + '}';
    }

    @Override
    public int compare(Studentt s1, Studentt s2) {
        return Integer.compare(s1.age, s2.age);
    }
}