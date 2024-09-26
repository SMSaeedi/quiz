package com.example.demo;

import lombok.Getter;

public class LazySingleToneClass {
    /**
     * Lazy initialize means the class initialized only when it's needed.
     * Pros: Simple implementation, instance is created only when needed.
     * Cons: Not thread-safe, multiple threads could create multiple instances
     */
    private static LazySingleToneClass lazySingleToneClass = null;

    private LazySingleToneClass() {
    }

    public static LazySingleToneClass getSingleToneClass() {
        if (lazySingleToneClass == null)
            return lazySingleToneClass = new LazySingleToneClass();
        return lazySingleToneClass;
    }
}

class ThreadSafeLazySingleton {
    /**
     * Pros: Thread-safe.
     * Cons: Synchronized method can reduce performance due to the overhead of acquiring locks.
     */
    private static ThreadSafeLazySingleton instance;

    private ThreadSafeLazySingleton() {
    }

    public static synchronized ThreadSafeLazySingleton getInstance() {
        if (instance == null) {
            instance = new ThreadSafeLazySingleton();
        }
        return instance;
    }
}

class DoubleCheckedLockingSingleton {
    /**
     * Pros: Thread-safe, improved performance by reducing synchronization overhead.
     * Cons: More complex implementation.
     */
    private static volatile DoubleCheckedLockingSingleton instance;

    private DoubleCheckedLockingSingleton() {
    }

    public static DoubleCheckedLockingSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckedLockingSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return instance;
    }
}

class BillPughSingleton {
    /**
     * Pros: Thread-safe, lazy initialization, and does not require synchronized methods.
     * Cons: None
     */
    private BillPughSingleton() {
    }

    private static class SingletonHelper {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
}

class EagerSingleToneClass {
    /**
     * Eager initialize means the SingleTon class is initialized on the class load.
     * Pros: Simple implementation, thread-safe without requiring synchronized methods.
     * Cons: Instance is created even if it is never used, which leads to resource wastage and memory cost
     */
    @Getter
    private final static EagerSingleToneClass eagerSingleToneClass = new EagerSingleToneClass();

    private EagerSingleToneClass() {
    }
}

class SingleTon {
    private static SingleTon instance;

    private SingleTon() {
        /** Simulate an expensive operation, like connecting to a database */
        System.out.println("SingleTon is being created...");
    }

    public static SingleTon getInstance() {
        if (instance == null)
            instance = new SingleTon();

        return instance;
    }

    public void performOperation() {
        System.out.println("Performing an operation on the singleton...");
    }
}

class SingletonProxy{
    private SingleTon singleton;

    public SingletonProxy() {
        /** The singleton is not created here*/
        System.out.println("Proxy created, singleton is not yet instantiated.");
    }

    public void performOperation() {
        /** Access control: Only allow access if some condition is met*/
        if (!hasAccess()) {
            System.out.println("Access denied to the singleton.");
            return;
        }

        /** Lazy initialization*/
        if (singleton == null) {
            System.out.println("Initializing the singleton inside the proxy...");
            singleton = SingleTon.getInstance();
        }

        /** Logging*/
        System.out.println("Logging: Singleton method is being called.");

        /** Delegate the call to the singleton*/
        singleton.performOperation();
    }

    private boolean hasAccess() {
        /** Simulate an access control check, such as checking user permissions*/
        return true; /** or false to simulate access denial*/
    }

    public static void main(String[] args) {
        SingletonProxy proxy = new SingletonProxy();

        /** First access: will trigger the creation of the singleton*/
        proxy.performOperation();

        /** Subsequent access: no need to re-create the singleton*/
        proxy.performOperation();
    }
}