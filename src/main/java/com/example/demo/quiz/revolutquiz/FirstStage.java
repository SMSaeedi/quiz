package com.example.demo.quiz.revolutquiz;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FirstStage {
    List<String> serverList = new ArrayList<>();
    AtomicInteger index = new AtomicInteger(0);

    public String addServer(String server) {
        if (!serverList.contains(server)) {
            serverList.add(server);
            return "Server added " + server;
        } else return "Server already exists";
    }

    public String removeServer(String server) {
        if (serverList.contains(server)) {
            serverList.remove(server);
            return "Server removed " + server;
        } else return "Server does not exist already";
    }

    public String findNextServer() {
        if (serverList.isEmpty())
            return "Server list is empty";
        /**
         * getAndUpdate to automatically update the current index and ensure the increment is thread-safe
         * '(o + 1) % serverList.size()' ensures if it reaches the end of list, wraps over to the start
         * */
        int andUpdate = index.getAndUpdate(o -> (o + 1) % serverList.size());
        return serverList.get(andUpdate);
    }
}
