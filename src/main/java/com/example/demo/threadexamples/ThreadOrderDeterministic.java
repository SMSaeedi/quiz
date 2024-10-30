package com.example.demo.threadexamples;

import lombok.SneakyThrows;

import static java.lang.System.out;

public class ThreadOrderDeterministic {
    @SneakyThrows
    public static void main(String[] args) {
        out.println("1");
        Thread t1 = new Thread() {
            public void run() {
                out.println("2");
            }
        };
        t1.start();
        t1.join();
        out.println("3");
        Thread t2 = new Thread() {
            public void run() {
                out.println("4");
            }
        };
        t2.start();
        t2.join();
        out.println("5");
        out.println("6");
    }
}

class ThreadOrderNonDeterministic {
    public static void main(String[] args) {
        out.println("1");
        Thread t1 = new Thread() {
            public void run() {
                out.println("2");
            }
        };
        t1.start();
        out.println("3");
        Thread t2 = new Thread() {
            public void run() {
                out.println("4");
            }
        };
        t2.start();
        out.println("5");
        out.println("6");
    }
}