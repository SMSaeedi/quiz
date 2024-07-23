package com.example.demo.threadexamples;

public class ThreadLocalSharedData {
    /**
     * Local threads are local variables in which each thread can access them through getter/setter,
     * and independently initialized copy of the variable in each thread.
     */
    private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        Runnable task1 = () -> {
            threadLocal.set(100);
            System.out.println("Thread 1 --> " + threadLocal.get());
        };

        Runnable task2 = () -> {
            threadLocal.set(200);
            System.out.println("Thread 2 --> " + threadLocal.get());
        };

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();
    }
}
