package com.example.demo.singleton;

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
     * Pros: Simple implementation, instance is created only when needed.
     * Cons: Not thread-safe, multiple threads could create multiple instances
     */
    private static LazySingleToneClass lazySingleToneClass = null;

    private LazySingleToneClass() {
    }

    public static LazySingleToneClass getSingleToneClass() {
        out.println("Lazy SingleTone is being called");
        if (lazySingleToneClass == null)
            lazySingleToneClass = new LazySingleToneClass();
        return lazySingleToneClass;
    }
}

class EagerSingleToneClassNumber_1 {
    /**
     * Eager initialize means the SingleTon class is initialized on the class load with an immutable object.
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
     * Cons: another Thread might see a partially initialized Singleton instance while calling getInstance()
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
     * Pros: Thread-safe locking with volatile, improved performance by reducing synchronization overhead.
     */
    private static volatile DoubleCheckedLockingSingleton instance;

    private DoubleCheckedLockingSingleton() {
    }

    public static DoubleCheckedLockingSingleton getInstance() {
        out.println("Thread safe Lazy SingleTone with volatile instance and block locked is being called");
        if (instance == null)  //1st check without locking
            synchronized (DoubleCheckedLockingSingleton.class) {
                if (instance == null)  //2nd check with locking
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
        private static final BillPughSingleton INSTANCE = new BillPughSingleton(); //Immutable object for Shared State
    }

    public static BillPughSingleton getInstance() {
        out.println("Pugh SingleTone with immutable instance is being called");
        return SingletonHelper.INSTANCE;
    }
}