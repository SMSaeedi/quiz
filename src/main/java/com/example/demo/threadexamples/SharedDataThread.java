package com.example.demo.threadexamples;

public class SharedDataThread implements Runnable {
    private final SharedData sharedData;

    public SharedDataThread(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Override
    public void run() {
        int data = sharedData.getData();
        sharedData.setData(data + 2);
    }

    public static void main(String[] args) {
        SharedData data = new SharedData();
        data.setData(244);

        Thread thread1 = new Thread(new SharedDataThread(data));
        Thread thread2 = new Thread(new SharedDataThread(data));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("final value: " + data.getData());
    }
}

class SharedData {
    private int data;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
