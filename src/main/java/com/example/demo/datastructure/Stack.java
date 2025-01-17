package com.example.demo.datastructure;

public class Stack {
    private int[] arr;
    private int top;
    private int capacity;

    public Stack(int size) {
        arr = new int[size];  // fixed-size array
        capacity = size;
        top = -1;  // Stack is initially empty
    }

    public void push(int value) {
        if (isFull())
            throw new StackOverflowError("Stack is full");

        arr[++top] = value;  // Increment the top and add the element
    }

    public int pop() {
        if (isEmpty())
            throw new RuntimeException("Stack is empty");

        return arr[top--];  // Return the top element and decrement the top
    }

    public int peek() {
        if (isEmpty())
            throw new RuntimeException("Stack is empty");

        return arr[top];
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return top == -1;
    }

    // Check if the stack is full
    public boolean isFull() {
        return top == capacity - 1;
    }

    // Get the current size of the stack
    public int size() {
        return top + 1;
    }
}