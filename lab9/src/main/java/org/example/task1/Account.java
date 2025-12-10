package org.example.task1;

import java.util.concurrent.atomic.AtomicInteger;

public class Account {
    private static final AtomicInteger idGenerator = new AtomicInteger(0);

    private final int id;
    private long balance;

    public Account(long initialBalance) {
        this.id = idGenerator.incrementAndGet();
        this.balance = initialBalance;
    }

    public void withdraw(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (balance < amount) {
            return;
        }
        balance -= amount;
    }

    public void deposit(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        balance += amount;
    }

    public long getBalance() {
        return balance;
    }

    public int getId() {
        return id;
    }
}