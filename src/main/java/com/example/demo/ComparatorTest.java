package com.example.demo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComparatorTest {
    public static void main(String[] args) {
        List<MyString> list = new ArrayList<>();
        list.add(new MyString("Ali", 36, 11, LocalDate.of(1988, 4, 4)));
        list.add(new MyString("Mahsa", 33, 8, LocalDate.of(1991, 4, 4)));
        list.add(new MyString("Sara", 38, 16, LocalDate.of(1986, 4, 16)));

        list.sort(new MyComparator());
        list.forEach(System.out::println);
    }
}

record MyString(String name, int age, int yearsExperience, LocalDate birthDay) {
}

class MyComparator implements Comparator<MyString> {
    @Override
    public int compare(MyString o1, MyString o2) {
        if (o1.age() > o2.age()) return 1;
        if (o1.age() < o2.age()) return -1;
        if (o1.yearsExperience() > o2.yearsExperience()) return 1;
        if (o1.yearsExperience() < o2.yearsExperience()) return -1;
        if (o1.birthDay().isAfter(o2.birthDay())) return 1;
        if (o1.birthDay().isBefore(o2.birthDay())) return -1;
        return 0;
    }
}