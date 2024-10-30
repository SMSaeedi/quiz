package com.example.demo.threadexamples;

import lombok.SneakyThrows;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static java.lang.System.out;

public class ThreadLocalSharedData {
    /**
     * Local threads are local variables in which each thread can access them through getter/setter,
     * and each thread independently initialized copy of the variable.
     */
    private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    @SneakyThrows
    public static void main(String[] args) {
        Runnable task1 = () -> {
            threadLocal.set(100);
            out.println("Thread 1 --> " + threadLocal.get());
        };

        Runnable task2 = () -> {
            threadLocal.set(200);
            out.println("Thread 2 --> " + threadLocal.get());
        };

        Runnable task3 = () -> {
            threadLocal.set(250);
            out.println("thread 3 --> " + threadLocal.get());
        };

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        Thread thread3 = new Thread(task3);

        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        thread3.start();
    }
}
class Test {
    public static void main(String[] args) {
        Future<String> future=new Future<String>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public String get() throws InterruptedException, ExecutionException {
                return "";
            }

            @Override
            public String get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return "";
            }
        };
    }

}