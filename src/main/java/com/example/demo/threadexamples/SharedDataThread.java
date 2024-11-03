package com.example.demo.threadexamples;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.System.out;

@Getter
@Setter
class SimpleIntSharedData {
    public int data;

    public SimpleIntSharedData(int sharedData) {
        this.data = sharedData;
    }

    @SneakyThrows
    public static void main(String[] args) {
        SimpleIntSharedData simpleIntSharedData = new SimpleIntSharedData(100);

        Runnable task1 = () -> {
            simpleIntSharedData.setData(25);
            out.println("1st thread --> " + simpleIntSharedData.getData());
        };

        Runnable task2 = () -> {
            simpleIntSharedData.setData(35);
            out.println("2nd thread --> " + simpleIntSharedData.getData());
        };

        Runnable task3 = () -> {
            simpleIntSharedData.setData(150);
            out.println("3nd thread --> " + simpleIntSharedData.getData());
        };

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        Thread thread3 = new Thread(task3);

        thread1.start();
        thread1.join(); //wait the thread/s until thread1 finish

        thread2.start();
        thread2.join();

        thread3.start();
        thread3.join();

        out.println("Shared data final value --> " + simpleIntSharedData.getData());
    }
}

class AtomicSharedData {
    public AtomicInteger sharedData;

    public AtomicSharedData(int value) {
        sharedData = new AtomicInteger(value);
    }

    public static void main(String[] args) {
        AtomicSharedData useSharedData = new AtomicSharedData(100);

        Runnable task1 = () -> {
            useSharedData.sharedData.addAndGet(25);
            out.println("1st thread --> " + useSharedData.sharedData.get());
        };

        Runnable task2 = () -> {
            useSharedData.sharedData.addAndGet(35);
            out.println("2nd thread --> " + useSharedData.sharedData.get());
        };

        Runnable task3 = () -> {
            useSharedData.sharedData.addAndGet(150);
            out.println("3rd thread --> " + useSharedData.sharedData.get());
        };

        try (ExecutorService executor = Executors.newFixedThreadPool(3)) {
            executor.submit(task3);
            executor.submit(task1);
            executor.submit(task2);

            executor.shutdown();
        }
    }
}

class ConcurrentHashMapSharedData {
    public ConcurrentHashMap<String, Integer> concurrentMap;

    public ConcurrentHashMapSharedData(int value) {
        concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.put("data", value);
    }

    public void modifyingSharedData(int data) {
        /*
         * merge() is a convenient way to update a value based on its current value,
         * and is particularly useful for atomic updates in a concurrent environment
        */
        concurrentMap.merge("data", data, Integer::sum);
    }

    public static void main(String[] args) {
        ConcurrentHashMapSharedData useSharedData = new ConcurrentHashMapSharedData(100);

        Runnable task1 = () -> {
            useSharedData.modifyingSharedData(25);
            out.println("1st thread --> " + useSharedData.concurrentMap.get("data"));
        };

        Runnable task2 = () -> {
            useSharedData.modifyingSharedData(35);
            out.println("2nd thread --> " + useSharedData.concurrentMap.get("data"));
        };

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(task1);
        executor.submit(task2);

        executor.shutdown();
    }
}

class ReentrantLockSharedData {
    private int sharedData;
    private final Lock lock = new ReentrantLock();

    public ReentrantLockSharedData(int initialValue) {
        this.sharedData = initialValue;
    }

    public void setData(int value) {
        lock.lock();
        try {
            sharedData += value;
        } finally {
            lock.unlock();
        }
    }

    public int getData() {
        lock.lock();
        try {
            return sharedData;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockSharedData reentrantLockSharedData = new ReentrantLockSharedData(100);

        Runnable task1 = () -> {
            reentrantLockSharedData.setData(25);
            out.println("1st thread --> " + reentrantLockSharedData.getData());
        };

        Runnable task2 = () -> {
            reentrantLockSharedData.setData(35);
            out.println("2nd thread --> " + reentrantLockSharedData.getData());
        };

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(task1);
        executor.submit(task2);

        executor.shutdown();
    }
}