package org.example;

import java.util.List;
import java.util.Scanner;

public class BookView {
    private Scanner scanner;

    public BookView() {
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        System.out.println("\n========================================");
        System.out.println("       BOOK MANAGEMENT SYSTEM          ");
        System.out.println("========================================");
        System.out.println("1. Show all books");
        System.out.println("2. Find books by author");
        System.out.println("3. Find books by publisher");
        System.out.println("4. Find books published after year");
        System.out.println("5. Sort books by publisher");
        System.out.println("0. Exit");
        System.out.println("========================================");
        System.out.print("Choose option: ");
    }

    public int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void showBooks(List<String> books, String title) {
        System.out.println("\n" + "=".repeat(120));
        System.out.printf("%60s%n", title);
        System.out.println("=".repeat(120));

        if (books.isEmpty()) {
            System.out.println("No books found!");
        } else {
            System.out.println("-".repeat(120));
            books.forEach(System.out::println);
            System.out.println("-".repeat(120));
            System.out.println("Total: " + books.size() + " book(s)");
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showSearchInfo(String type, String value) {
        System.out.println("\nSearching " + type + ": \"" + value + "\"");
    }

    public void close() {
        scanner.close();
    }
}
