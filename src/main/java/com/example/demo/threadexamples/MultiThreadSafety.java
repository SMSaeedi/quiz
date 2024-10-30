package com.example.demo.threadexamples;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.System.out;

/**
 * Multi-threads run concurrently without interfering each other
 */
public class MultiThreadSafety {
    private final ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(10);

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
        MultiThreadSafety mt = new MultiThreadSafety();

        for (int i = 0; i < 5; i++)
            mt.process("key");

        mt.shutdown();
    }
}
