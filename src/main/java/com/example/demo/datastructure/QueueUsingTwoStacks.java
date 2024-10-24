package com.example.demo.datastructure;

import java.util.Stack;

class QueueUsingTwoStacks {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    // Constructor to initialize the queue
    public QueueUsingTwoStacks() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    // Enqueue an element into the queue
    public void enqueue(int value) {
        stack1.push(value);  // Always push into stack1
    }

    // Dequeue an element from the queue
    public int dequeue() {
        if (isEmpty())
            throw new RuntimeException("Queue is empty");

        // If stack2 is empty, transfer all elements from stack1 to stack2
        if (stack2.isEmpty())
            while (!stack1.isEmpty())
                stack2.push(stack1.pop());

        // Pop from stack2 to dequeue the front element
        return stack2.pop();
    }

    // Peek the front element of the queue without dequeuing it
    public int peek() {
        if (isEmpty())
            throw new RuntimeException("Queue is empty");

        if (stack2.isEmpty())
            while (!stack1.isEmpty())
                stack2.push(stack1.pop());

        return stack2.peek();
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}