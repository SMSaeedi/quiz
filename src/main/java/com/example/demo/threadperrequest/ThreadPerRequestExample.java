package com.example.demo.threadperrequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.System.out;

/**
 * each incoming request is handled by a separate thread.
 * This is a simple model for handling concurrent requests,
 * and is often used in web-server or network-server applications,
 * where each client request is handled independently.
 * *
 * Pros: Simplicity, handling the concurrent requests
 * Cons: CPU and memory cost, scalability issue (in case of large number of concurrent requests)
 */
public class ThreadPerRequestExample {
    private static final int PORT = 8080;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            out.println("Server started on port " + PORT);

            while (true) {
                // Accept incoming client connections
                Socket clientSocket = serverSocket.accept();
                out.println("New client connected");

                // Handle the request in a new thread
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);

                // Echo the received message back to the client
                out.println("Echo: " + inputLine);

                // Close connection if the client sends "exit"
                if ("exit".equalsIgnoreCase(inputLine)) {
                    break;
                }
            }

            System.out.println("Client disconnected");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}