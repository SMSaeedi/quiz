package com.example.demo.threadexamples;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.lang.System.out;

public class FutureExample {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        out.println("before submit..");

        Future<Integer> future = executorService.submit(() -> {
            int sum = 0;
            for (int i = 1; i <= 5; i++) {
                sum += i;
                Thread.sleep(500);
            }
            return sum;
        });

        out.println("after submit..");
        out.println("before get..");

        int k = 2;
        while (!future.isDone()) {
            out.println("still working..");
            out.println("k is " + k);
            k += 2;
            Thread.sleep(500);
        }

        try {
            out.println("" + future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        out.println("finish main()..");
    }
}
