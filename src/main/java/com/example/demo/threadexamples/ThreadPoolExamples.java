package com.example.demo.threadexamples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.System.out;

/**
 * Multi-threads run concurrently without interfering each other
 */
public class ThreadPoolExamples {
    private final ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(3);

    public void process(String key) {
        executor.submit(() -> {
            map.merge(key, 1, Integer::sum);
            out.println(map.get(key));
        });
    }

    public void shutdown() {
        executor.shutdown();
    }

    public static void main(String[] args) {
        ThreadPoolExamples mt = new ThreadPoolExamples();

        for (int i = 0; i < 5; i++)
            mt.process("key");

        mt.shutdown();
    }
}

class SingleThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Collection tasks = new ArrayList<>();

        tasks.add((Callable<Integer>) () -> {
            int sum = 0;
            for (int i = 0; i < 3; i++) {
                sum += i;
                out.println("task 1 " + sum);
                Thread.sleep(50);
            }
            return sum;
        });

        tasks.add((Callable<Integer>) () -> {
            int multiple = 0;
            for (int i = 0; i < 3; i++) {
                multiple *= i;
                out.println("task 2 " + multiple);
                Thread.sleep(50);
            }
            return multiple;
        });

        tasks.add((Callable<Double>) () -> {
            double mod = 1.0;
            for (int i = 1; i < 3; i++) {
                mod %= i;
                out.println("task 3 " + mod);
                Thread.sleep(50);
            }
            return mod;
        });

        try {
            executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdown();
    }
}