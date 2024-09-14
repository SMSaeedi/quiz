package com.example.demo.threadexamples;

public class RaceConditionTest {
    private int regularInt = 5;

    public int addAndGet(int val) {
        regularInt += val;
        return regularInt;
    }

    public int getAndAdd(int val) {
        int temp = regularInt;
        regularInt += val;
        return temp;
    }

    public int get() {
        return regularInt;
    }

    public static void main(String[] args) {
        RaceConditionTest test = new RaceConditionTest();

        new Thread(() -> System.out.println(test.addAndGet(10))).start();
        new Thread(() -> System.out.println(test.getAndAdd(5))).start();

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            System.out.println(test.get());
        }
    }
}