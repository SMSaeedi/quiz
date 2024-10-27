package com.example.demo.solid.lsp.violation;

import static java.lang.System.out;

public class LSP {
    public static void main(String[] args) {
        Bird eagle = new Eagle();
        Bird penguin = new Penguin();

        eagle.fly(); // Works fine
        penguin.fly(); // Throws UnsupportedOperationException
    }
}

class Bird {
    public void fly() {
        out.println("Bird is flying");
    }
}

class Eagle extends Bird {
    // Eagle can fly, so it does not override the fly method
}

class Penguin extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Penguins can't fly");
    }
}
