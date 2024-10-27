package com.example.demo.solid.ia;

import static java.lang.System.out;

public class InterfaceAggregation {
    public static void main(String[] args) {
        Car myCar = new Car();

        myCar.startEngine();
        myCar.drive();
        myCar.steerLeft();
        myCar.applyBrakes();
        myCar.stopEngine();
    }
}

interface Vehicle {
    void drive();
}

interface Engine {
    void startEngine();

    void stopEngine();
}

interface Steering {
    void steerLeft();

    void steerRight();
}

interface Braking {
    void applyBrakes();
}

class Car implements Vehicle, Engine, Steering, Braking {
    @Override
    public void drive() {
        startEngine();
        out.println("Driving the car");
    }

    @Override
    public void startEngine() {
        out.println("Engine started");
    }

    @Override
    public void stopEngine() {
        out.println("Engine stopped");
    }

    @Override
    public void steerLeft() {
        out.println("Steering left");
    }

    @Override
    public void steerRight() {
        out.println("Steering right");
    }

    @Override
    public void applyBrakes() {
        out.println("Applying brakes");
    }
}