package org.example.task1;

public class Bank {

    public void transfer(Account from, Account to, int amount) {
        if (from == to || amount <= 0) {
            return;
        }

        Account lock1 = from.getId() < to.getId() ? from : to;
        Account lock2 = from.getId() < to.getId() ? to : from;

        synchronized (lock1) {
            synchronized (lock2) {
                if (from.getBalance() >= amount) {
                    from.withdraw(amount);
                    to.deposit(amount);
                }
            }
        }
    }
}