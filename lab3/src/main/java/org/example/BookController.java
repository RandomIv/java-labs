package org.example;

import java.util.List;

public class BookController {
    private BookModel bookModel;
    private BookView bookView;
    public BookController(BookModel bookModel, BookView bookView) {
        this.bookModel = bookModel;
        this.bookView = bookView;
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
        bookView.showBooks(bookModel.getBooks(), "ALL BOOKS");
    }

    private void searchByAuthor() {
        String author = bookModel.getRandomAuthor();
        bookView.showSearchInfo("by author", author);

        List<Book> result = bookModel.findBooksByAuthor(author);
        bookView.showBooks(result, "BOOKS BY " + author.toUpperCase());
        if (result.isEmpty()) bookView.showMessage("No books found for this author!");
    }

    private void searchByPublisher() {
        String publisher = bookModel.getRandomPublisher();
        bookView.showSearchInfo("by publisher", publisher);

        List<Book> result = bookModel.findBooksByPublisher(publisher);
        bookView.showBooks(result, "BOOKS BY " + publisher.toUpperCase());
        if (result.isEmpty()) bookView.showMessage("No books found for this publisher!");
    }

    private void searchByYear() {
        int year = bookModel.getRandomYear();
        bookView.showSearchInfo("after year", String.valueOf(year));

        List<Book> result = bookModel.findBooksAfterYear(year);
        bookView.showBooks(result, "BOOKS PUBLISHED AFTER " + year);
        if (result.isEmpty()) bookView.showMessage("No books found after " + year + "!");
    }

    private void sortByPublisher() {
        List<Book> sorted = bookModel.sortBooksByPublisher();
        bookView.showBooks(sorted, "BOOKS SORTED BY PUBLISHER");
    }
}
