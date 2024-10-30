package com.example.demo.threadexamples;

import lombok.SneakyThrows;

import static java.lang.System.out;

public class DaemonThread {

    public static void main(String[] args) {
        Thread t1 = new Thread() {
            @SneakyThrows
            public void run() {
                for (int i = 0; i < 10; i++) {
                    out.println("t1 " + i + " of 10");
                    sleep(10);
                }
            }
        };

        Thread t2 = new Thread() {
            @SneakyThrows
            public void run() {
                for (int i = 0; i < 30; i++) {
                    out.println("t2 " + i + " of 30");
                    sleep(10);
                }
            }
        };
        t1.setDaemon(true);

        Thread t3 = new Thread() {
            @SneakyThrows
            public void run() {
                for (int i = 0; i < 20; i++) {
                    out.println("t3 " + i + " of 20");
                    sleep(10);
                }
            }
        };

        t1.start();
        t2.start();
        t3.start();
    }
}
