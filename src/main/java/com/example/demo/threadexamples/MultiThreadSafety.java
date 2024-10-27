package com.example.demo.threadexamples;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.System.out;

/**
 * Multi-thread safety extends the concept into the entire app/system,
 * ensuring multi-threads run concurrently without interfering each other
 * Multi-thread safety a mutex for a single resource (A Mutex (short for Mutual Exclusion) is a synchronization
 * primitive used in concurrent programming to control access to a shared resource)
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
