package com.example.demo.solid.lsp;

import static java.lang.System.out;

public class LSP {
    public static void main(String[] args) {
        FlyingBird eagle = new Eagle();
        Bird penguin = new Penguin();

        eagle.fly(); // Works fine
        penguin.makeSound(); // Works fine, no fly method for penguin
    }
}

class Bird {
    public void makeSound() {
        out.println("Bird is making a sound");
    }
}

class FlyingBird extends Bird {
    public void fly() {
        out.println("Bird is flying");
    }
}

class Eagle extends FlyingBird {
    // Eagle can fly, so it does not override the fly method
}

class Penguin extends Bird {
    // Penguins can't fly, so they do not have a fly method
    @Override
    public void makeSound() {
        out.println("Penguin is making a sound");
    }
}