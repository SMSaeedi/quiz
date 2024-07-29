package com.example.demo.threadexamples;

import java.util.concurrent.*;

public class PriorityBlockingQueue implements Runnable, Comparable<PriorityBlockingQueue> {
    private final int priority;
    private final String taskName;

    public PriorityBlockingQueue(int priority, String taskName) {
        this.priority = priority;
        this.taskName = taskName;
    }

    @Override
    public int compareTo(PriorityBlockingQueue o) {
        return Integer.compare(this.priority, o.priority);
    }

    @Override
    public void run() {
        System.out.println("Executing task: " + taskName + " with priority: " + priority);
    }
}

class PriorityBlockingQueueExample {
    public static void main(String[] args) throws InterruptedException {
        java.util.concurrent.PriorityBlockingQueue<Runnable> orderedQueue = new java.util.concurrent.PriorityBlockingQueue<>();
        try (ExecutorService executor = new ThreadPoolExecutor
                (3, 3, 0L, TimeUnit.MICROSECONDS, orderedQueue)) {

            executor.submit(new PriorityBlockingQueue(1, "High priority task"));
            executor.submit(new PriorityBlockingQueue(3, "Low priority task"));
            executor.submit(new PriorityBlockingQueue(2, "Medium priority task"));

            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.MINUTES);
        }
    }
}