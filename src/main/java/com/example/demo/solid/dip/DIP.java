package com.example.demo.solid.dip;

public class DIP {

    public static void main(String[] args) {
        PaymentProcessor creditCardProcessor = new CreditCardProcessor();
        PaymentProcessor payPalProcessor = new PayPalProcessor();

        OrderProcessor orderProcessor1 = new OrderProcessor(creditCardProcessor);
        orderProcessor1.processOrder(100.0);

        OrderProcessor orderProcessor2 = new OrderProcessor(payPalProcessor);
        orderProcessor2.processOrder(150.0);
    }
}

interface PaymentProcessor {
    void processPayment(double amount);
}

class CreditCardProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + " using Credit Card");
    }
}

class PayPalProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + " using PayPal");
    }
}

class OrderProcessor {
    private PaymentProcessor paymentProcessor;

    public OrderProcessor(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public void processOrder(double amount) {
        // Some order processing logic
        paymentProcessor.processPayment(amount);
    }
}