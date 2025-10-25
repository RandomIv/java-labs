package org.example;

import java.util.List;

public class BookController {
    private BookModel bookModel;
    private BookView bookView;
    public BookController(BookModel bookModel, BookView bookView) {
        this.bookModel = bookModel;
        this.bookView = bookView;
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
        String author = bookModel.getRandomAuthor();
        bookView.showSearchInfo("by author", author);

        List<Book> books = bookModel.findBooksByAuthor(author);
        List<String> formattedBooks = formatBooksToStrings(books);
        
        bookView.showBooks(formattedBooks, "BOOKS BY " + author.toUpperCase());
        if (formattedBooks.isEmpty()) bookView.showMessage("No books found for this author!");
    }

    private void searchByPublisher() {
        String publisher = bookModel.getRandomPublisher();
        bookView.showSearchInfo("by publisher", publisher);

        List<Book> books = bookModel.findBooksByPublisher(publisher);
        List<String> formattedBooks = formatBooksToStrings(books);

        bookView.showBooks(formattedBooks, "BOOKS BY " + publisher.toUpperCase());
        if (formattedBooks.isEmpty()) bookView.showMessage("No books found for this publisher!");
    }

    private void searchByYear() {
        int year = bookModel.getRandomYear();
        bookView.showSearchInfo("after year", String.valueOf(year));

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
}
