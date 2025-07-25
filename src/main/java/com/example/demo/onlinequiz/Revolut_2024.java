package com.example.demo.onlinequiz;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

class Revolut {
    public List<String> serverList = new ArrayList<>();
    private final AtomicInteger index = new AtomicInteger(0);

    public void registerServer(final String server) {
        if(server == null)
            throw new ServerException("null ip address!");

        try {
            InetAddress addressIP = InetAddress.getByName(server);
            validateIp(addressIP.getHostAddress());
            serverList.add(addressIP.getHostAddress());
        } catch (UnknownHostException e) {
            throw new ServerException("unknown host");
        }
    }

    private void validateIp(final String server) {
        if (server.length() >= 12) throw new ServerException("out of domain range!");
        if (serverList.contains(server)) throw new ServerException("duplicated IP address!");
    }

    public String findNextServer() {
        if (serverList.isEmpty())
            return "Server list is empty";
        /*
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

class UrlShort {
    // Revolut Quiz 2025: given the original url, generate the short url, then mapping it where the original url stored
    static AtomicInteger integer = new AtomicInteger(0);
    static Map<String, String> map = new HashMap<>();

    public static String registerBaseUrl(String baseUrl) {
        String shortUrl = "https://www.rev.me/" + integer.incrementAndGet();
        map.put(shortUrl, baseUrl);
        return shortUrl;
    }

    public static String receiveBaseUrl(String shortUrl) {
        return map.get(shortUrl);
    }

    public static void main(String[] args) {
        System.out.println(registerBaseUrl("https://www.revolut.com/rewards-personalised-cashback-and-discounts/"));
        System.out.println(receiveBaseUrl("https://www.rev.me/1"));
    }
}