package com.example.demo.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TCP server for the {@link BankService}.
 *
 * <p>Commands are one per line:
 * <pre>
 * CREATE accountId initialBalance
 * DEPOSIT accountId amount
 * WITHDRAW accountId amount
 * TRANSFER fromAccountId toAccountId amount
 * BALANCE accountId
 * HELP
 * QUIT
 * </pre>
 */
public class BankTcpServer implements AutoCloseable {
    public static final int DEFAULT_PORT = 5000;
    private static final int MAX_CLIENTS = 50;

    private final BankService bankService;
    private final int port;
    private final ExecutorService clientExecutor;

    public BankTcpServer(BankService bankService, int port) {
        if (bankService == null) {
            throw new IllegalArgumentException("Bank service must not be null");
        }
        if (port < 1 || port > 65535) {
            throw new IllegalArgumentException("Port must be between 1 and 65535");
        }
        this.bankService = bankService;
        this.port = port;
        this.clientExecutor = Executors.newFixedThreadPool(MAX_CLIENTS);
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Bank TCP server listening on port " + port);
            while (!serverSocket.isClosed()) {
                Socket client = serverSocket.accept();
                System.out.println("Client connected: " + client.getRemoteSocketAddress());
                clientExecutor.submit(new ClientHandler(client));
            }
        } finally {
            close();
        }
    }

    @Override
    public void close() {
        clientExecutor.shutdown();
    }

    private class ClientHandler implements Runnable {
        private final Socket socket;

        private ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (Socket client = socket;
                 BufferedReader input = new BufferedReader(
                         new InputStreamReader(client.getInputStream()));
                 PrintWriter output = new PrintWriter(client.getOutputStream(), true)) {

                output.println("OK Connected. Type HELP for commands.");
                String command;
                while ((command = input.readLine()) != null) {
                    System.out.println("Received from " + client.getRemoteSocketAddress() + ": " + command);

                    if (command.isBlank()) {
                        output.println("ERROR Command must not be blank");
                        System.out.println("Sent to " + client.getRemoteSocketAddress()
                                + ": ERROR Command must not be blank");
                        continue;
                    }

                    if ("QUIT".equalsIgnoreCase(command.trim())) {
                        output.println("OK Goodbye");
                        System.out.println("Sent to " + client.getRemoteSocketAddress() + ": OK Goodbye");
                        break;
                    }

                    String response = execute(command);
                    output.println(response);
                    System.out.println("Sent to " + client.getRemoteSocketAddress() + ": " + response);
                }
                System.out.println("Client disconnected: " + client.getRemoteSocketAddress());
            } catch (IOException e) {
                System.err.println("Client connection failed: " + e.getMessage());
            }
        }
    }

    private String execute(String command) {
        String[] parts = command.trim().split("\\s+");
        String operation = parts[0].toUpperCase();

        try {
            return switch (operation) {
                case "CREATE" -> {
                    requireArguments(parts, 3);
                    bankService.createAccount(parts[1], amount(parts[2]));
                    yield "OK Account created";
                }
                case "DEPOSIT" -> {
                    requireArguments(parts, 3);
                    bankService.deposit(parts[1], amount(parts[2]));
                    yield "OK Deposit completed";
                }
                case "WITHDRAW" -> {
                    requireArguments(parts, 3);
                    bankService.withdraw(parts[1], amount(parts[2]));
                    yield "OK Withdrawal completed";
                }
                case "TRANSFER" -> {
                    requireArguments(parts, 4);
                    bankService.transfer(parts[1], parts[2], amount(parts[3]));
                    yield "OK Transfer completed";
                }
                case "BALANCE" -> {
                    requireArguments(parts, 2);
                    yield "OK " + bankService.getBalance(parts[1]);
                }
                case "HELP" -> "OK CREATE, DEPOSIT, WITHDRAW, TRANSFER, BALANCE, HELP, QUIT";
                default -> "ERROR Unknown command";
            };
        } catch (IllegalArgumentException e) {
            return "ERROR " + e.getMessage();
        }
    }

    private static BigDecimal amount(String value) {
        try {
            BigDecimal amount = new BigDecimal(value);
            if (amount.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Amount must be positive");
            }
            return amount;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Amount must be a valid number");
        }
    }

    private static void requireArguments(String[] parts, int expectedCount) {
        if (parts.length != expectedCount) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }
    }

    public static void main(String[] args) throws IOException {
        int port = args.length == 0 ? DEFAULT_PORT : Integer.parseInt(args[0]);
        BankService bankService = new BankService();
        new BankTcpServer(bankService, port).start();
    }
}
