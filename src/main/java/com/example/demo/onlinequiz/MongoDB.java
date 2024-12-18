package com.example.demo.onlinequiz;

import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

class MongoDB {
    public static void main(String[] args) {
        String str = "Hello";
        int num = 10;

        modifyValues(str, num);
        System.out.println("String after modification: " + str); //Hello
        System.out.println("Integer after modification: " + num); //10

        MyObj myObj = modifyValues1(str, num);
        System.out.println("String after modification: " + myObj.str); //World
        System.out.println("Integer after modification: " + myObj.num); //20
    }

    public static void modifyValues(String str, int num) {
        str = "World";
        num = 20;
    }

    public static MyObj modifyValues1(String str, int num) {
        str = "World";
        num = 20;
        return new MyObj(str, num);
    }

    @AllArgsConstructor
    @Accessors(fluent = true)
    static class MyObj {
        String str;
        int num;
    }
}

class ClassPerson {
    String name;

    ClassPerson(String name) {
        this.name = name;
    }

    public static void modifyObject(ClassPerson person) {
        person = new ClassPerson("Jon");
    }

    public static ClassPerson modifyObject2(ClassPerson person) {
        person = new ClassPerson("Jon");
        return person;
    }

    public static void main(String[] args) {
        ClassPerson person = new ClassPerson("Jane");
        modifyObject(person);
        System.out.println(person.name); // What will be printed here? Jane

        ClassPerson person2 = new ClassPerson("Jane");
        person2 = modifyObject2(person2);
        System.out.println(person2.name); // What will be printed here? Jon
    }
}

abstract class Shape {
    // Abstract method for calculating area
    public abstract double getArea();

    public static void main(String[] args) {
        // Create a list of Shape objects (mix of Circle and Rectangle)
        List<Shape> list = new ArrayList<>();
        list.add(new Rectangle(5, 2));
        list.add(new Circle(4));

        // Print the area of each shape
        for (Shape shape : list)
            System.out.println(shape.getArea());
    }
}

class Circle extends Shape {
    double radius;

    public Circle(double i) {
        this.radius = i;
    }

    // Circle-specific fields and implementation
    @Override
    public double getArea() {
        // implement it base on Circle shape
        return 3.14 * radius * radius;
    }
}

class Rectangle extends Shape {
    double height, width;

    public Rectangle(double i, double i1) {
        this.height = i;
        this.width = i1;
    }

    // Rectangle-specific fields and implementation
    @Override
    public double getArea() {
        // implement it base on Circle shape
        return height * width;
    }
}

/**
 * DB question
 * Given the following table structure:
 * Person:
 * - id
 * - first_name
 * - last_name
 * Person_Address:
 * - person_id
 * - address_id
 * Address:
 * - id
 * - street
 * - city
 * - zipcode
 * Write a SQL query that lists how many people live on each address?
 * Example output:
 * address_id | street               |  no_tenants
 * --------------------------------------------------------
 * a          |  Goodge Street  73   |  3
 * b          |  Long Lane 1023      |  17
 * c          |  Queens Road 7       |  1
 * Query:
 * SELECT pa.address_id, a.street, COUNT(pa.person_id) AS no_tenants
 * FROM Person_Address pa JOIN Address a ON pa.address_id = a.id
 * GROUP BY pa.address_id, a.street
 * ORDER BY pa.address_id;
 */