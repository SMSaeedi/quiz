package com.example.demo.solid.dip.violation;

public class DIP {
    public static void main(String[] args) {
        OrderProcessor orderProcessor = new OrderProcessor();
        orderProcessor.processOrder(100.0);
    }
}

class CreditCardProcessor {
    public void processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + " using Credit Card");
    }
}

class OrderProcessor {
    private CreditCardProcessor creditCardProcessor;

    public OrderProcessor() {
        this.creditCardProcessor = new CreditCardProcessor();
    }

    public void processOrder(double amount) {
        // Some order processing logic
        creditCardProcessor.processPayment(amount);
    }
}