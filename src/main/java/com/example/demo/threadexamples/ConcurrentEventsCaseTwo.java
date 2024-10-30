package com.example.demo.threadexamples;

import static java.lang.System.out;

class ConcurrentEventsCaseOne {
    public static void main(String[] args) {
        Thread e4 = new Thread(new Event4());
        Thread e1 = new Thread(new Event1(e4));
        Thread e2 = new Thread(new Event2(e4));

        e1.start();
        e2.start();
        e4.start();

        try {
            e1.join();
            e2.join();
            e4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        out.println("All tasks completed");
    }
}

class Event1 implements Runnable {
    Thread event4;

    public Event1(Thread event4) {
        this.event4 = event4;
    }

    @Override
    public void run() {
        try {
            /**
             * pause the execution of current Thread for 1000 ms
             * Thread lifecycle: Running -> Timed-Waiting
             */
            event4.join();
            out.println("Event1 is running ... ");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        out.println("Event1 is finished ... ");
    }
}

class Event2 implements Runnable {
    Thread event4;

    public Event2(Thread event4) {
        this.event4 = event4;
    }

    @Override
    public void run() {
        try {
            event4.join();
            out.println("Event2 is running ... ");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        out.println("Event2 is finished ... ");
    }
}

class Event4 implements Runnable {

    @Override
    public void run() {
        out.println("Event4 is running ... ");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        out.println("Event4 is finished ... ");
    }
}

class ConcurrentEventsCaseTwo {
    public static void main(String[] args) {
        Runnable two = () -> {
            out.println("Two is running ... ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            out.println("Two is finished ... ");
        };

        Thread t2 = new Thread(two);
        Runnable four = () -> {
            try {
                t2.join();
                out.println("Four is running ... ");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            out.println("Four is finished ... ");
        };

        Runnable five = () -> {
            try {
                t2.join();
                out.println("Five is running ... ");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            out.println("Five is finished ... ");
        };

        Thread t4 = new Thread(four);
        Thread t5 = new Thread(five);

        t2.start();
        t4.start();
        t5.start();

        try {
            t2.join();
            t4.join();
            t5.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        out.println("All tasks completed.");
    }
}

class ConcurrentEventsCaseThree {
    public static void main(String[] args) {
        Runnable three = () -> {
            out.println("Three is running ... ");
            for (int i = 0; i < 3; i++)
                out.println("Three task number: " + i);
            out.println("Three is finished ... ");
        };

        Thread t3 = new Thread(three);
        t3.start();
        out.println("Task completed.");
    }
}

class ConcurrentEventsCaseFour {
    /**
     * use Thread.sleep() in case of delays or long-running tasks.
     */
    public static void main(String[] args) {
        Runnable alpha = () -> {
            out.println("Alpha is running ... ");
            for (int i = 0; i < 2; i++)
                out.println("Alpha task number: " + i);
            out.println("Alpha is finished ... ");
        };

        Runnable gamma = () -> {
            out.println("Gamma is running ... ");
            for (int i = 0; i < 3; i++)
                out.println("Gamma task number: " + i);
            out.println("Gamma is finished ... ");
        };

        Thread tAlpha = new Thread(alpha);
        Thread tGamma = new Thread(gamma);

        tAlpha.start();
        tGamma.start();

        try {
            tAlpha.join();
            tGamma.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        out.println("All tasks completed.");
    }
}