package com.example.demo.revolutquiz;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThirdStage {
    //locking mechanism
    private final Lock lock = new ReentrantLock();

    public Integer lockingResultExample(int count) {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
        return count;
    }

    //deadLock
    public void deadLockExample(String resource1, String resource2) {
        Thread t1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("t1 is running, locking resource1");

                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (resource2) {
                    System.out.println("t1 is running, locking resource2");
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (resource2) {
                System.out.println("t2 is running, locking resource2");

                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (resource1) {
                    System.out.println("t2 is running, locking resource1");
                }
            }
        });
        t1.start();
        t2.start();
    }

    //threadPool
    public void threadPoolExample(ExecutorService executor) {
        ThreadPoolWorker worker;
        for (int i = 0; i < 5; i++) {
            worker = new ThreadPoolWorker("" + i);
            executor.execute(worker);
        }
        executor.shutdown();
    }
}

class ThreadPoolWorker implements Runnable {
    private String count;

    public ThreadPoolWorker(String count) {
        this.count = count;
    }

    public void processCount() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " process starts: " + count);
        processCount();
        System.out.println(Thread.currentThread().getName() + " process finishes: ");
    }
}