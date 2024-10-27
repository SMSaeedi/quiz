package com.example.demo.solid.ocp;

import lombok.Getter;

import static java.lang.System.out;

public class OCP {
    public static void main(String[] args) {
        Shape circle = new Circle(5);
        Shape rectangle = new Rectangle(4, 6);
        Shape triangle = new Triangle(3, 4);

        AreaCalculator calculator = new AreaCalculator();

        out.println("Circle area: " + calculator.calculateArea(circle));
        out.println("Rectangle area: " + calculator.calculateArea(rectangle));
        out.println("Triangle area: " + calculator.calculateArea(triangle));
    }
}

interface Shape {
    double calculateArea();
}

@Getter
class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

@Getter
class Rectangle implements Shape {
    private double length;
    private double breadth;

    public Rectangle(double length, double breadth) {
        this.length = length;
        this.breadth = breadth;
    }

    @Override
    public double calculateArea() {
        return length * breadth;
    }
}

@Getter
class Triangle implements Shape {
    private double base;
    private double height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return 0.5 * base * height;
    }
}

class AreaCalculator {
    public double calculateArea(Shape shape) {
        return shape.calculateArea();
    }
}