package com.example.demo.quiz;

import java.util.ArrayList;
import java.util.List;

class FirstQuiz {
    public List<String> serverList = new ArrayList<>();

    public void registerServer(final String server) {
        if (serverList.size() >= 10) throw new ServerException("out of threshold");

        validateAndAddServer(server);
    }

    public boolean validateAndAddServer(final String server) {
        var serverLength = server.length();
        if (!serverList.contains(server)) {
            if (serverLength >= 11 && serverLength <= 12) {
                serverList.add(server);
                return true;
            }
            return false;
        }
        return false;
    }
}

class ServerException extends RuntimeException {

    public ServerException(String message) {
        super(message);
    }
}