package com.example.demo;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Multi-thread safety extends the concept into the entire app/system,
 * ensuring multiple threads run concurrently without interfering each other
 * Multi-thread safety a mutex for a single resource
 */
public class MTSafety {
    private final ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    public void process(String key) {
        executor.submit(() -> {
            map.merge(key, 1, Integer::sum);
            System.out.println(map.get(key));
        });
    }

    public void shutdown() {
        executor.shutdown();
    }

    public static void main(String[] args) {
        MTSafety mt = new MTSafety();

        for (int i = 0; i < 5; i++)
            mt.process("key");

        mt.shutdown();
    }
}
