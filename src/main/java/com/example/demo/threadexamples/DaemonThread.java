package com.example.demo.threadexamples;

import static java.lang.System.out;

public class DaemonThread {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                out.println("t1 " + i + " of 10");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                out.println("t2 " + i + " of 30");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t1.setDaemon(true);

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                out.println("t3 " + i + " of 20");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
