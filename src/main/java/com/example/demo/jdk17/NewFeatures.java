package com.example.demo.jdk17;

import static java.lang.System.out;

/**
 * Immutable by default, fields in records are final and cannot be changed.
 * Compact syntax, Java generates the constructor, equals(), hashCode(), and toString() methods.
 * Ideal for use in data transfer objects (DTOs), immutable data structures,
 * or wherever minimalistic, immutable, and value-based classes are needed.
 * */
public record NewFeatures(int id, String name) {
    public static void main(String[] args) {
        NewFeatures newFeatures = new NewFeatures(1, "Records");
        out.println(newFeatures.id());
        out.println(newFeatures.name());
        out.println(newFeatures);
    }
}

/**
 * Sealed classes restrict which classes can extend them using the permits clause.
 * Classes that extend a sealed class must be either final, sealed, or non-sealed.
 * This is useful for enforcing a limited and controlled hierarchy, making the code more maintainable and secure.
 * Useful in scenarios where you want to ensure controlled inheritance in frameworks or APIs,
 * and you want to restrict the subclasses to a predefined set.
 */
sealed abstract class Shape permits Circle, Rectangle {
    abstract double area();
}

final class Circle extends Shape {
    double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI * radius * radius;
    }
}

final class Rectangle extends Shape {
    double length;
    double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    double area() {
        return length * width;
    }
}

class Main {
    public static void main(String[] args) {
        Shape circle = new Circle(3);
        Shape rectangle = new Rectangle(2, 5);

        out.println(circle.area());
        out.println(rectangle.area());
    }
}

class TypePatternSwitchCase {
    public static void main(String[] args) {
        Object object = 10;
        var result = switch (object) {
            case Integer i -> "Integer " + (i * 2);
            case Double d -> "Double " + (d / 2);
            case String s -> "String" + s.toString();
            default -> "Unexpected type";
        };
        out.println(result);
    }
}