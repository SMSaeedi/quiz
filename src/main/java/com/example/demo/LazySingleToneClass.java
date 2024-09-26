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

/**
Lazy Initialization:
A proxy can help in delaying the creation of the singleton instance until it is actually needed.
 This can be useful if the singleton object is resource-intensive to create,
 and you want to avoid instantiating it during the startup of the application.

 Access Control:
A proxy can control access to the singleton instance.
 For example, you might want to restrict certain operations or ensure that only authorized components
 of the application can access specific methods of the singleton.

 Logging, Auditing, and Monitoring:
Proxies can be used to add logging, auditing, or monitoring behavior without modifying the singleton class.
 The proxy can intercept method calls, log them, and then delegate the calls to the actual singleton instance.

 Remote Access:
If the singleton object needs to be accessed across different JVMs (e.g., in a distributed application),
 a proxy can act as a local representative that communicates with the remote singleton instance.

 Mocking for Testing:
Proxies can be used to mock or stub the singleton instance during testing.
 This allows for more flexible and controlled unit tests without the need to modify the singleton class.

 Effectiveness:
Using a proxy pattern with a singleton largely depends on the specific requirements of the application.
 If the use case benefits from the additional functionality that a proxy provides
 (e.g., lazy initialization, access control, etc.), then it can be very effective.

 Overhead:
Introducing a proxy adds a layer of indirection, which could introduce some performance overhead. However,
 in most cases, this overhead is negligible compared to the benefits it provides.

 Complexity:
Using a proxy pattern can add complexity to your design.
 It's important to ensure that this complexity is justified by the added benefits.

 When to Use:
1. You need to control or restrict access to the singleton.
2. You want to add additional behavior like logging, monitoring, or lazy initialization.
3. You are working in a distributed environment where remote access to the singleton is required.
4. You need to mock the singleton in unit tests.

 When not to Use:
1. The singleton is simple and does not require any additional behavior.
2. The added complexity does not justify the benefits.*/

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