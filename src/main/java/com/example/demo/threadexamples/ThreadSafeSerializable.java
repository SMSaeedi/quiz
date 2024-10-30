package com.example.demo.threadexamples;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Serializable makes the object converting into byte stream, which means deserializable back into the copy of the object.
 * Thread-safe serialization a single mutex (is a synchronization mechanism to control access to a shared resource)
 * for all resources
 */
public class ThreadSafeSerializable implements Serializable {
    private static final long serialVersionUID = 1L;
    private int count;

    public synchronized int getCount() {
        return count;
    }

    public synchronized void incrementCount() {
        count++;
    }

    public synchronized void objRead(ObjectInputStream obj) throws IOException, ClassNotFoundException {
        obj.defaultReadObject();
    }

    public synchronized void objWrite(ObjectOutputStream obj) throws IOException, ClassNotFoundException {
        obj.defaultWriteObject();
    }
}
