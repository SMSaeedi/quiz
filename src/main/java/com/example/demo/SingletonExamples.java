package com.example.demo;

import lombok.Getter;

import static java.lang.System.out;

public class SingletonExamples {
    public static void main(String[] args) {
        out.println(LazySingleToneClass.getSingleToneClass());
        out.println(EagerSingleToneClassNumber_1.getEagerSingleToneClass());
        out.println(EagerSingleToneClassNumber_2.EAGER_INSTANCE);
        out.println(ThreadSafeMethodSingleton.getInstance());
        out.println(ThreadSafeBlockSingleton.getInstance());
        out.println(DoubleCheckedLockingSingleton.getInstance());
        out.println(BillPughSingleton.getInstance());
    }
}

class LazySingleToneClass {
    /**
     * Lazy initialize means the class initialized only when it's needed.
     * Pros: Simple implementation, instance is created only when needed.
     * Cons: Not thread-safe, multiple threads could create multiple instances
     */
    private static LazySingleToneClass lazySingleToneClass = null;

    private LazySingleToneClass() {
    }

    public static LazySingleToneClass getSingleToneClass() {
        out.println("Lazy SingleTone is being called");
        if (lazySingleToneClass == null)
            return lazySingleToneClass = new LazySingleToneClass();
        return lazySingleToneClass;
    }
}

class EagerSingleToneClassNumber_1 {
    /**
     * Use Immutable Objects for Shared State
     * Eager initialize means the SingleTon class is initialized on the class load.
     * Pros: Simple implementation, thread-safe without requiring synchronized methods.
     * Cons: Instance is created even if it is never used, which leads to resource wastage and memory cost
     */
    @Getter
    private final static EagerSingleToneClassNumber_1 eagerSingleToneClass = new EagerSingleToneClassNumber_1();

    private EagerSingleToneClassNumber_1() {
        out.println("Eager SingleTon with private instance is being called");
    }
}

class EagerSingleToneClassNumber_2 {
    public final static EagerSingleToneClassNumber_2 EAGER_INSTANCE = new EagerSingleToneClassNumber_2();
}

class ThreadSafeMethodSingleton {
    /**
     * Pros: Thread-safe.
     * Cons: Synchronized method can reduce performance due to the overhead of acquiring locks.
     */
    private static ThreadSafeMethodSingleton instance;

    private ThreadSafeMethodSingleton() {
    }

    public static synchronized ThreadSafeMethodSingleton getInstance() {
        out.println("Thread safe Lazy SingleTone with method locked is being called");
        if (instance == null)
            instance = new ThreadSafeMethodSingleton();
        return instance;
    }
}

class ThreadSafeBlockSingleton {
    /**
     * Pros: Thread-safe.
     * Cons: Synchronized method can reduce performance due to the overhead of acquiring locks.
     */
    private static ThreadSafeBlockSingleton instance;

    private ThreadSafeBlockSingleton() {
    }

    public static ThreadSafeBlockSingleton getInstance() {
        out.println("Thread safe Lazy SingleTone with method locked is being called");
        if (instance == null)
            synchronized (ThreadSafeBlockSingleton.class) {
                instance = new ThreadSafeBlockSingleton();
            }
        return instance;
    }
}

class DoubleCheckedLockingSingleton {
    /**
     * Double-Checked Locking with volatile
     * Pros: Thread-safe, improved performance by reducing synchronization overhead.
     * Cons: More complex implementation.
     */
    private static volatile DoubleCheckedLockingSingleton instance;

    private DoubleCheckedLockingSingleton() {
    }

    public static DoubleCheckedLockingSingleton getInstance() {
        out.println("Thread safe Lazy SingleTone with volatile instance and block locked is being called");
        if (instance == null)
            synchronized (DoubleCheckedLockingSingleton.class) {
                instance = new DoubleCheckedLockingSingleton();
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
        /**
         * Use Immutable Objects for Shared State
         */
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        out.println("Pugh SingleTone with immutable instance is being called");
        return SingletonHelper.INSTANCE;
    }
}

/**
 * A proxy can help in delaying the creation of the singleton instance until it is actually needed.
 * This can be useful if the singleton object is resource-intensive to create,
 * and you want to avoid instantiating it during the startup of the application.
 * <p>
 * When to Use:
 * 1. You need to control or restrict access to the singleton.
 * 2. You want to add additional behavior like logging, monitoring, or lazy initialization.
 * 3. You are working in a distributed environment where remote access to the singleton is required.
 * 4. You need to mock the singleton in unit tests.
 * <p>
 * When not to Use:
 * 1. The singleton is simple and does not require any additional behavior.
 * 2. The added complexity does not justify the benefits.
 */
class SingleTon {
    private static SingleTon instance;

    private SingleTon() {
        /** Simulate an expensive operation, like connecting to a database */
        out.println("SingleTon is being created...");
    }

    public static SingleTon getInstance() {
        if (instance == null)
            instance = new SingleTon();
        return instance;
    }

    public void performOperation() {
        out.println("Performing an operation on the singleton...");
    }
}

class SingletonProxy {
    private SingleTon singleton;

    public SingletonProxy() {
        /** The singleton is not created here*/
        out.println("Proxy created, singleton is not yet instantiated.");
    }

    public void performOperation() {
        /** Access control: Only allow access if some condition is met*/
        if (!hasAccess()) {
            out.println("Access denied to the singleton.");
            return;
        }

        /** Lazy initialization*/
        if (singleton == null) {
            out.println("Initializing the singleton inside the proxy...");
            singleton = SingleTon.getInstance();
        }

        /** Logging*/
        out.println("Logging: Singleton method is being called.");

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