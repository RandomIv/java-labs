package org.example;

public record Book(
        String title,
        String author,
        String publisher,
        int year,
        int pages,
        double price
) implements java.io.Serializable {}