package com.example.demo.quiz;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

class FirstQuiz {
    public List<String> serverList = new ArrayList<>();
    private AtomicInteger index = new AtomicInteger(0);

    public void registerServer(final String server) {
        validateIp(server);

        try {
            InetAddress addressIP = InetAddress.getByName(server);
            serverList.add(addressIP.getHostAddress());
        } catch (UnknownHostException e) {
            throw new ServerException("unknown host");
        }
    }

    private void validateIp(final String server) {
        if (serverList.size() >= 10) throw new ServerException("out of threshold");
        if (serverList.contains(server)) throw new ServerException("IP address already exist");
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

class ServerException extends RuntimeException {
    public ServerException(String message) {
        super(message);
    }
}