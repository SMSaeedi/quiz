package com.example.demo.threadexamples;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.System.out;

public class ReentrantLockExample {
    Lock lock = new ReentrantLock();

    void m1() {
        out.println(Thread.currentThread().getName() + " m1 is executing");
        lock.lock();
        out.println(Thread.currentThread().getName() + " m1 is locking");
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
        out.println(Thread.currentThread().getName() + " m1 is released");
        out.println(Thread.currentThread().getName() + " m1 is exiting");
    }

    void m2() {
        out.println(Thread.currentThread().getName() + " m2 is executing");
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        out.println(Thread.currentThread().getName() + " m2 is exiting");
    }
}

class TestLock {
    static ReentrantLockExample lockObj = new ReentrantLockExample();

    public static void main(String[] args) {
        Thread t1 = new Thread() {
            public void run() {
                out.println("t1 started");
                lockObj.m1();
            }
        };

        Thread t2 = new Thread() {
            public void run() {
                out.println("t2 started");
                lockObj.m1();
            }
        };

        t1.setName("Tread 1");
        t1.start();

        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {

        }

        t2.setName("Tread 2");
        t2.start();
    }
}