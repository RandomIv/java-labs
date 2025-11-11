package org.example;

import java.io.*;
import java.util.List;

public class BookFileHandler {

    public void saveBooks(List<Book> books, String filename) throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            objectOutputStream.writeObject(books);
        }
    }

    public List<Book> loadBooks(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<Book>) objectInputStream.readObject();
        }
    }
}