package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class BookModel {
    private List<Book> books;
    private Random random;

    private final String[] titles = {
            "The Great Gatsby", "To Kill a Mockingbird", "1984", "Pride and Prejudice",
            "The Catcher in the Rye", "Lord of the Flies", "Jane Eyre", "Wuthering Heights",
            "The Lord of the Rings", "Harry Potter", "The Hobbit", "Brave New World"
    };

    private final String[] authors = {
            "F. Scott Fitzgerald", "Harper Lee", "George Orwell", "Jane Austen",
            "J.D. Salinger", "William Golding", "Charlotte Bronte", "Emily Bronte",
            "J.R.R. Tolkien", "J.K. Rowling", "Aldous Huxley", "John Steinbeck"
    };

    private final String[] publishers = {
            "Penguin Random House", "HarperCollins", "Simon & Schuster", "Hachette",
            "Macmillan", "Scholastic", "Oxford University Press", "Cambridge Press",
            "Vintage Books", "Bantam Books", "Doubleday", "Little Brown"
    };

    public BookModel() {
        this.books = new ArrayList<>();
        this.random = new Random();
        initializeBooks();
    }

    private void initializeBooks() {
        for (int i = 0; i < 12; i++) {
            books.add(new Book(
                    titles[i],
                    authors[i],
                    publishers[random.nextInt(publishers.length)],
                    1990 + random.nextInt(35),
                    100 + random.nextInt(600),
                    10 + random.nextDouble() * 40
            ));
        }
    }
    public List<Book> getBooks() { return books; }
    public String getRandomAuthor() { return authors[random.nextInt(authors.length)]; }
    public String getRandomPublisher() { return publishers[random.nextInt(publishers.length)]; }
    public int getRandomYear() { return 1990 + random.nextInt(34); }
    public List<Book> findBooksByAuthor(String author) {
        return books.stream().filter(book -> book.author().equalsIgnoreCase(author)).toList();
    }

    public List<Book> findBooksByPublisher(String publisher) {
        return books.stream()
                .filter(book -> book.publisher().equalsIgnoreCase(publisher))
                .toList();
    }

    public List<Book> findBooksAfterYear(int year) {
        return books.stream()
                .filter(book -> book.year() > year)
                .toList();
    }

    public List<Book> sortBooksByPublisher() {
        return books.stream()
                .sorted(Comparator.comparing(Book::publisher, String.CASE_INSENSITIVE_ORDER))
                .toList();
    }
}
