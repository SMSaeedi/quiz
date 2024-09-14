package com.example.demo.quiz;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

class FirstQuiz {
    public List<String> serverList = new ArrayList<>();

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
}

class ServerException extends RuntimeException {
    public ServerException(String message) {
        super(message);
    }
}