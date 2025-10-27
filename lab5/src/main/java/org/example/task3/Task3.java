package org.example.task3;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class Task3 {

    public static void main(String[] args) {
        String originalFile = "original.txt";
        String encryptedFile = "encrypted.txt";
        String decryptedFile = "decrypted.txt";

        char key = 'S';
        int keyCode = key;

        System.out.println("Encryption key: " + key + " (code: " + keyCode + ")");

        try {
            try (Writer writer = new FileWriter(originalFile)) {
                writer.write("This is a test line.\nIt will be encrypted.");
            }
            System.out.println("1. Created original file: " + originalFile);
            System.out.println("   Content:\n" + readFile(originalFile) + "\n");

            System.out.println("2. Encrypting...");
            try (Reader reader = new FileReader(originalFile);
                 Writer writer = new EncryptingWriter(new FileWriter(encryptedFile), keyCode)) {
                int c;
                while ((c = reader.read()) != -1) {
                    writer.write(c);
                }
            }
            System.out.println("   Created encrypted file: " + encryptedFile);
            System.out.println("   Content (unreadable):\n" + readFile(encryptedFile) + "\n");

            System.out.println("3. Decrypting...");
            try (Reader reader = new DecryptingReader(new FileReader(encryptedFile), keyCode);
                 Writer writer = new FileWriter(decryptedFile)) {
                int c;
                while ((c = reader.read()) != -1) {
                    writer.write(c);
                }
            }
            System.out.println("   Created decrypted file: " + decryptedFile);
            System.out.println("   Content (should match original):\n" + readFile(decryptedFile) + "\n");

        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
    }

    private static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (Reader reader = new FileReader(filePath)) {
            int c;
            while ((c = reader.read()) != -1) {
                content.append((char) c);
            }
        }
        return content.toString();
    }
}
