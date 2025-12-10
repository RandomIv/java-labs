package org.example.task1;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Task1 {
    private static final int NUM_ACCOUNTS = 100;
    private static final int INITIAL_BALANCE = 1000;
    private static final int NUM_THREADS = 8;
    private static final int NUM_TRANSFERS = 500_000;

    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();
        Random random = new Random();

        Account[] accounts = new Account[NUM_ACCOUNTS];
        for (int i = 0; i < NUM_ACCOUNTS; i++) {
            accounts[i] = new Account(INITIAL_BALANCE);
        }

        long initialTotal = Stream.of(accounts)
                .mapToLong(Account::getBalance)
                .sum();
        System.out.println("Initial total balance: " + initialTotal);

        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        System.out.println("Running " + NUM_TRANSFERS + " transfers...");
        for (int i = 0; i < NUM_TRANSFERS; i++) {
            executor.submit(() -> {
                Account from = accounts[random.nextInt(NUM_ACCOUNTS)];
                Account to = accounts[random.nextInt(NUM_ACCOUNTS)];
                int amount = random.nextInt(INITIAL_BALANCE / 10);

                bank.transfer(from, to, amount);
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        long finalTotal = Stream.of(accounts)
                .mapToLong(Account::getBalance)
                .sum();
        System.out.println("Final total balance:   " + finalTotal);

        if (initialTotal == finalTotal) {
            System.out.println("SUCCESS: Balances match!");
        } else {
            System.err.println("ERROR: Balances do not match!");
        }
    }
}
