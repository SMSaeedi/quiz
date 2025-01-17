package com.example.demo.datastructure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    @Test
    void testPush() {
        Stack stack = new Stack(3);
        stack.push(1);
        stack.push(2);

        assertEquals(2, stack.size());
        assertEquals(2, stack.peek());
    }

    @Test
    void testPushThrowsStackOverflowError() {
        Stack stack = new Stack(2);
        stack.push(1);
        stack.push(2);

        assertThrows(StackOverflowError.class, () -> stack.push(3));
    }

    @Test
    void testPop() {
        Stack stack = new Stack(3);
        stack.push(1);
        stack.push(2);

        int poppedValue = stack.pop();
        assertEquals(2, poppedValue);
        assertEquals(1, stack.size());
    }

    @Test
    void testPopThrowsExceptionWhenEmpty() {
        Stack stack = new Stack(3);

        assertThrows(RuntimeException.class, stack::pop);
    }

    @Test
    void testPeek() {
        Stack stack = new Stack(3);
        stack.push(1);
        stack.push(2);

        assertEquals(2, stack.peek());
        assertEquals(2, stack.size());
    }

    @Test
    void testPeekThrowsExceptionWhenEmpty() {
        Stack stack = new Stack(3);

        assertThrows(RuntimeException.class, stack::peek);
    }

    @Test
    void testIsEmpty() {
        Stack stack = new Stack(3);

        assertTrue(stack.isEmpty());
        stack.push(1);
        assertFalse(stack.isEmpty());
    }

    @Test
    void testIsFull() {
        Stack stack = new Stack(2);
        assertFalse(stack.isFull());

        stack.push(1);
        stack.push(2);

        assertTrue(stack.isFull());
    }

    @Test
    void testSize() {
        Stack stack = new Stack(3);

        assertEquals(0, stack.size());
        stack.push(1);
        stack.push(2);

        assertEquals(2, stack.size());

        stack.pop();
        assertEquals(1, stack.size());
    }
}