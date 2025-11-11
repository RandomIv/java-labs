package org.example;

import java.io.IOException;
import java.util.List;

public class BookController {
    private BookModel bookModel;
    private BookView bookView;
    private BookFileHandler fileHandler;

    public BookController(BookModel bookModel, BookView bookView) {
        this.bookModel = bookModel;
        this.bookView = bookView;
        this.fileHandler = new BookFileHandler();
    }

    private List<String> formatBooksToStrings(List<Book> books) {
        return books.stream()
                .map(book -> String.format(
                        "%-30s | %-25s | %-20s | %d | %4d pp. | $%.2f",
                        book.title(),
                        book.author(),
                        book.publisher(),
                        book.year(),
                        book.pages(),
                        book.price()
                ))
                .toList();
    }
    public void start() {
        boolean running = true;
        while (running) {
            bookView.showMenu();
            int choice = bookView.getChoice();

            switch (choice) {
                case 1 -> showAllBooks();
                case 2 -> searchByAuthor();
                case 3 -> searchByPublisher();
                case 4 -> searchByYear();
                case 5 -> sortByPublisher();
                case 6 -> saveBooks();
                case 7 -> loadBooks();
                case 0 -> {
                    running = false;
                    bookView.showMessage("Goodbye!");
                }
                default -> bookView.showMessage("Invalid choice!");
            }
        }
        bookView.close();
    }

    private void showAllBooks() {
        List<Book> books = bookModel.getBooks();
        List<String> formattedBooks = formatBooksToStrings(books);
        bookView.showBooks(formattedBooks, "ALL BOOKS");
    }

    private void searchByAuthor() {
        String author = bookView.getSearchQuery("author");
        List<Book> books = bookModel.findBooksByAuthor(author);
        List<String> formattedBooks = formatBooksToStrings(books);
        bookView.showBooks(formattedBooks, "BOOKS BY " + author.toUpperCase());
        if (formattedBooks.isEmpty()) bookView.showMessage("No books found for this author!");
    }

    private void searchByPublisher() {
        String publisher = bookView.getSearchQuery("publisher");
        List<Book> books = bookModel.findBooksByPublisher(publisher);
        List<String> formattedBooks = formatBooksToStrings(books);
        bookView.showBooks(formattedBooks, "BOOKS BY " + publisher.toUpperCase());
        if (formattedBooks.isEmpty()) bookView.showMessage("No books found for this publisher!");
    }

    private void searchByYear() {
        int year = bookView.getSearchYear();
        if (year == -1) {
            bookView.showMessage("Invalid input. Please enter a number.");
            return;
        }
        List<Book> books = bookModel.findBooksAfterYear(year);
        List<String> formattedBooks = formatBooksToStrings(books);
        bookView.showBooks(formattedBooks, "BOOKS PUBLISHED AFTER " + year);
        if (formattedBooks.isEmpty()) bookView.showMessage("No books found after " + year + "!");
    }

    private void sortByPublisher() {
        List<Book> books = bookModel.sortBooksByPublisher();
        List<String> formattedBooks = formatBooksToStrings(books);
        bookView.showBooks(formattedBooks, "BOOKS SORTED BY PUBLISHER");
    }

    private void saveBooks() {
        String filename = bookView.getFilename("save");
        try {
            fileHandler.saveBooks(bookModel.getBooks(), filename);
            bookView.showMessage("Books saved successfully to " + filename);
        } catch (IOException e) {
            bookView.showMessage("Error saving file: " + e.getMessage());
        }
    }

    private void loadBooks() {
        String filename = bookView.getFilename("load");
        try {
            List<Book> loadedBooks = fileHandler.loadBooks(filename);
            bookModel.setBooks(loadedBooks);
            bookView.showMessage("Books loaded successfully from " + filename);
            showAllBooks();
        } catch (IOException | ClassNotFoundException e) {
            bookView.showMessage("Error loading file: " + e.getMessage());
        } catch (Exception e) {
            bookView.showMessage("An unexpected error occurred: " + e.getMessage());
        }
    }
}