package com.example.demo.solid.ocp.violation;

import lombok.Getter;

import static java.lang.System.out;

public class OCP {
    public static void main(String[] args) {
        Circle circle = new Circle(5);
        Rectangle rectangle = new Rectangle(4, 6);

        AreaCalculator calculator = new AreaCalculator();

        out.println("Circle area: " + calculator.calculateArea(circle));
        out.println("Rectangle area: " + calculator.calculateArea(rectangle));
    }
}

class AreaCalculator {
    public double calculateArea(Object shape) {
        if (shape instanceof Circle) {
            Circle circle = (Circle) shape;
            return Math.PI * circle.getRadius() * circle.getRadius();
        } else if (shape instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) shape;
            return rectangle.getLength() * rectangle.getBreadth();
        }
        return 0;
    }
}

@Getter
class Circle {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

}

@Getter
class Rectangle {
    private double length;
    private double breadth;

    public Rectangle(double length, double breadth) {
        this.length = length;
        this.breadth = breadth;
    }

}