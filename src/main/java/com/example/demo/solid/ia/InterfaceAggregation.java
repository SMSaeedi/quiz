package com.example.demo.solid.ia;

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
        System.out.println("Driving the car");
    }

    @Override
    public void startEngine() {
        System.out.println("Engine started");
    }

    @Override
    public void stopEngine() {
        System.out.println("Engine stopped");
    }

    @Override
    public void steerLeft() {
        System.out.println("Steering left");
    }

    @Override
    public void steerRight() {
        System.out.println("Steering right");
    }

    @Override
    public void applyBrakes() {
        System.out.println("Applying brakes");
    }
}