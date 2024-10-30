package com.example.demo.singleton;

import static java.lang.System.out;

/**
 * A proxy can help in delaying the creation of the singleton instance until it is actually needed.
 * This can be useful if the singleton object is resource-intensive to create,
 * and you want to avoid instantiating it during the startup of the application.
 *
 * To Use:
 * 1. You need to control or restrict access to the singleton.
 * 2. You want to add additional behavior like logging, monitoring, or lazy initialization.
 * 3. You are working in a distributed environment where remote access to the singleton is required.
 * 4. You need to mock the singleton in unit tests.
 *
 * Not to use:
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
        /** The singleton is not created here */
        out.println("Proxy created, singleton is not yet instantiated.");
    }

    public void performOperation() {
        /** Access control: Only allow access if some condition is met */
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

        /** Delegate the call to the singleton */
        singleton.performOperation();
    }

    private boolean hasAccess() {
        /** Simulate an access control check, such as checking user permissions */
        return true; /** or false to simulate access denial */
    }

    public static void main(String[] args) {
        SingletonProxy proxy = new SingletonProxy();

        /** First access: will trigger the creation of the singleton */
        proxy.performOperation();

        /** Subsequent access: no need to re-create the singleton */
        proxy.performOperation();
    }
}