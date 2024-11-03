package com.example.demo.acm;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.System.out;
/**
 * Serialization is saved an object's state into a stream of bytes to restore it later.
 * */
public class Serialization {
    public static void main(String[] args) {
        BankAccount acc = new BankAccount(123, "Saving", 15000L);
//        serialized(acc);
        deSerialized(acc);
    }

    private static Object deSerialized(BankAccount acc) {
        Object originalObj = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("account.ser"))) {
            originalObj = ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        out.println("Serialized: " + originalObj);
        return originalObj;
    }

    private static void serialized(BankAccount acc) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("account.ser"))) {
            oos.writeObject(acc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

record BankAccount(int id, String accName, long balance) implements Serializable {
    private static final long serialVersionUID = 3L;
}

class ChatSocket {
    class ClientSocket {
        public static void main(String[] args) throws Exception {
            try {
                Socket socket = new Socket("127.0.0.1", 1234);
                out.println("Client:");

                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                Scanner sc = new Scanner(System.in);

                for (int i = 0; i < 3; i++) {
                    String text = (String) in.readObject(); // deSerialize from server
                    System.out.println(text);
                    out.writeObject(sc.nextLine()); //serialize to server
                    out.flush();
                }
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class ServerListener {
        public static void main(String[] args) {
            try {
                ServerSocket listener = new ServerSocket(1234);
                out.println("ServerSocket:");

                Scanner sc = new Scanner(System.in);

                Socket socket = listener.accept();
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                for (int i = 0; i < 3; i++) {
                    out.writeObject(sc.nextLine()); //serialize to client
                    out.flush();
                    String text = (String) in.readObject(); //deSerialize from client
                    System.out.println(text);
                }
                socket.close();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}