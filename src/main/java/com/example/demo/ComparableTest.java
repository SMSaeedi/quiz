package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComparableTest {
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("Mahsa", 30));
        studentList.add(new Student("Sara", 38));
        studentList.add(new Student("Mahshid", 29));
        studentList.add(new Student("Dara", 27));
        studentList.add(new Student("Ali", 30));

        Collections.sort(studentList);
        System.out.println(studentList);
    }
}

class Student implements Comparable<Student> {
    String name;
    int age;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return "Student{name: " + name + ", age: " + age + '}';
    }

    @Override
    public int compareTo(Student student) {
        return Integer.compare(this.age, student.age);
    }

}