package com.example.demo;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class BankService {

    private static class Account {
        private BigDecimal balance;
        private final ReentrantLock lock = new ReentrantLock();

        public Account(String accountId, BigDecimal initialBalance) {
            this.balance = initialBalance;
        }

        public void deposit(BigDecimal amount) {
            if (amount.compareTo(BigDecimal.ZERO) < 0)
                throw new IllegalArgumentException("Amount must be positive");
            lock.lock();
            try {
                balance = balance.add(amount);
            } finally {
                lock.unlock();
            }
        }

        public void withdraw(BigDecimal amount) {
            if (amount.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Amount must be positive");
            lock.lock();
            try {
                if (balance.compareTo(amount) < 0)
                    throw new IllegalArgumentException("Insufficient funds");
                balance = balance.subtract(amount);
            } finally {
                lock.unlock();
            }
        }

        public BigDecimal getBalance() {
            lock.lock();
            try {
                return balance;
            } finally {
                lock.unlock();
            }
        }
    }

    private final Map<String, Account> accounts = new ConcurrentHashMap<>();

    public void createAccount(String accountId, BigDecimal initialBalance) {
        accounts.putIfAbsent(accountId, new Account(accountId, initialBalance));
    }

    public void deposit(String accountId, BigDecimal amount) {
        Account account = getAccount(accountId);
        account.deposit(amount);
    }

    public void withdraw(String accountId, BigDecimal amount) {
        Account account = getAccount(accountId);
        account.withdraw(amount);
    }

    public void transfer(String fromAccountId, String toAccountId, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Amount must be positive");

        Account from = getAccount(fromAccountId);
        Account to = getAccount(toAccountId);

        // Lock ordering by accountId to avoid deadlocks
        Account first = fromAccountId.compareTo(toAccountId) < 0 ? from : to;
        Account second = fromAccountId.compareTo(toAccountId) < 0 ? to : from;

        first.lock.lock();
        second.lock.lock();
        try {
            from.withdraw(amount);
            to.deposit(amount);
        } finally {
            second.lock.unlock();
            first.lock.unlock();
        }
    }

    public BigDecimal getBalance(String accountId) {
        Account account = getAccount(accountId);
        return account.getBalance();
    }

    private Account getAccount(String accountId) {
        Account account = accounts.get(accountId);
        if (account == null) throw new IllegalArgumentException(STR."Account not found: \{accountId}");
        return account;
    }

    public static void main(String[] args) {
        BankService bank = new BankService();
        bank.createAccount("A", new BigDecimal("100.00"));
        bank.createAccount("B", new BigDecimal("50.00"));

        // Deposit Test
        bank.deposit("A", new BigDecimal("50.00"));
        assert bank.getBalance("A").equals(new BigDecimal("150.00")) : "Deposit test failed";

        // Withdraw Test
        bank.withdraw("A", new BigDecimal("20.00"));
        assert bank.getBalance("A").equals(new BigDecimal("130.00")) : "Withdraw test failed";

        // Transfer Test
        bank.transfer("A", "B", new BigDecimal("30.00"));
        assert bank.getBalance("A").equals(new BigDecimal("100.00")) : "Transfer from A failed";
        assert bank.getBalance("B").equals(new BigDecimal("80.00")) : "Transfer to B failed";

        // Exception Tests
        try {
            bank.withdraw("A", new BigDecimal("1000.00"));
            assert false : "Should have thrown for insufficient funds";
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Insufficient funds");
        }

        try {
            bank.transfer("A", "C", new BigDecimal("10.00"));
            assert false : "Should have thrown for non-existent account";
        } catch (IllegalArgumentException e) {
            assert e.getMessage().contains("Account not found");
        }

        System.out.println("All tests passed.");
    }

}
