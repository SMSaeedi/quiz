package com.example.demo.threadexamples;

import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.out;

class VolatileVariable extends Thread {
    @SneakyThrows
    public static void main(String[] args) {
        MyVolatileFlag myVolatileFlag = new MyVolatileFlag();
        myVolatileFlag.start();

        out.println("Thread starts and flag is true");
        Thread.sleep(2 * 1000);
        out.println("After 2 seconds we set flag false");
        myVolatileFlag.flag = false;
        out.println("Conclusion: if flag is volatile, thread will end");
        out.println("Conclusion: if flag is not volatile, thread will not end");
        out.println("Because the flag value is cached and cannot see the change!");

    }
}

class MyVolatileFlag extends Thread {
    /**
     * used to ensure that a variable's value is always read from and written to the main memory,
     * rather than being cached in the thread's local memory (CPU cache).
     * P.S. if the variable is either long or double (64 bits), for reading and writing the memory spaces for them,
     * do it in 32 bit blocks.
     */
    volatile boolean flag = true;

    public void run() {
        while (flag) ;
    }
}

class NonVolatileVariable extends Thread {
    @SneakyThrows
    public static void main(String[] args) {
        MyFlag myVolatileFlag = new MyFlag();
        myVolatileFlag.start();

        out.println("Thread starts and flag is true");
        Thread.sleep(2 * 1000);
        out.println("After 2 seconds we set flag false");
        myVolatileFlag.flag = false;
        out.println("Conclusion: if flag is volatile, thread will end");
        out.println("Conclusion: if flag is not volatile, thread will not end");
        out.println("Because the flag value is cached and cannot see the change!");

    }
}

class MyFlag extends Thread {
    boolean flag = true;

    public void run() {
        while (flag) ;
    }
}

class AtomicVariable {
    private static final AtomicInteger atomicCounter = new AtomicInteger(0);

    @SneakyThrows
    public static void main(String[] args) {
        Runnable incrementTask = () -> {
            for (int i = 0; i < 100; i++)
                atomicCounter.incrementAndGet();
        };

        Thread thread1 = new Thread(incrementTask);
        Thread thread2 = new Thread(incrementTask);

        thread1.start();
        thread1.join();

        thread2.start();
        thread2.join();
        out.println("Increment value of counter is " + atomicCounter.get());

        Runnable decrementTask = () -> {
            for (int i = 0; i < 50; i++)
                atomicCounter.decrementAndGet();
        };

        Thread thread3 = new Thread(decrementTask);
        Thread thread4 = new Thread(decrementTask);

        thread3.start();
        thread4.start();
        thread3.join();
        thread4.join();
        out.println("Decrement value of counter is " + atomicCounter.get());
    }
}