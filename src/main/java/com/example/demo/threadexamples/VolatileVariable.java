package com.example.demo.threadexamples;

import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileVariable {
    /**
     * Use Cases for volatile
     * State Flags: indicating the completion/cancellation of a task, or other simple state changes.
     *
     * Pros: Simplicity, Visibility, Performance
     * Cons: Does not provide atomicity for compound actions like incrementing a counter,
     * Suitable only for simple flags or state variables.
     * If not used carefully, it can lead to inefficient busy-waiting loops
     */
    private static volatile boolean flag = false;

    public static void main(String[] args) {
        Thread write = new Thread(() -> {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            flag = true;
            System.out.println("Thread write the flag true");
        });

        Thread read = new Thread(() -> {
            while (!flag) {
                System.out.println("flag is still true");
            }
            System.out.println("Thread Read teh flag has changed");
        });

        write.start();
        read.start();
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
        System.out.println("Increment value of counter is " + atomicCounter.get());

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
        System.out.println("Decrement value of counter is " + atomicCounter.get());
    }
}