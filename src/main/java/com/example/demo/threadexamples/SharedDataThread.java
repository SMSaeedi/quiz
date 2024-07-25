package com.example.demo.threadexamples;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
class SimpleIntSharedData {
    public int data;

    public SimpleIntSharedData(int sharedData) {
        this.data = sharedData;
    }

//    public void modifyingSharedData(int data) {
//        setData(data + 125);
//    }

    @SneakyThrows
    public static void main(String[] args) {
        SimpleIntSharedData simpleIntSharedData = new SimpleIntSharedData(100);

        Runnable task1 = () -> {
            simpleIntSharedData.setData(25);
            System.out.println("1st thread --> " + simpleIntSharedData.getData());
        };

        Runnable task2 = () -> {
            simpleIntSharedData.setData(35);
            System.out.println("2nd thread --> " + simpleIntSharedData.getData());
        };

        Runnable task3 = () -> {
            simpleIntSharedData.setData(150);
            System.out.println("3nd thread --> " + simpleIntSharedData.getData());
        };

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        Thread thread3 = new Thread(task3);

        thread1.start();
        thread1.join(); //wait the thread/s until thread1 to finish

        thread2.start();
        thread2.join();

        thread3.start();
        thread3.join();

        System.out.println("Shared data final value --> " + simpleIntSharedData.getData());
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
            System.out.println("1st thread --> " + useSharedData.sharedData.get());
        };

        Runnable task2 = () -> {
            useSharedData.sharedData.addAndGet(35);
            System.out.println("2nd thread --> " + useSharedData.sharedData.get());
        };

        Runnable task3 = () -> {
            useSharedData.sharedData.addAndGet(150);
            System.out.println("3rd thread --> " + useSharedData.sharedData.get());
        };

        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(task3);
        executor.submit(task1);
        executor.submit(task2);

        executor.shutdown();
    }
}

class ConcurrentHashMapSharedData {
    public ConcurrentHashMap<String, Integer> concurrentMap;

    public ConcurrentHashMapSharedData(int value) {
        concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.put("data", value);
    }

    public void modifyingSharedData(int data) {
        concurrentMap.merge("data", data, Integer::sum);
    }

    public static void main(String[] args) {
        ConcurrentHashMapSharedData useSharedData = new ConcurrentHashMapSharedData(100);

        Runnable task1 = () -> {
            useSharedData.modifyingSharedData(25);
            System.out.println("1st thread --> " + useSharedData.concurrentMap.get("data"));
        };

        Runnable task2 = () -> {
            useSharedData.modifyingSharedData(35);
            System.out.println("2nd thread --> " + useSharedData.concurrentMap.get("data"));
        };

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(task1);
        executor.submit(task2);

        executor.shutdown();
    }
}