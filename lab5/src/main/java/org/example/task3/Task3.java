package org.example.task3;

import org.apache.log4j.Logger;

import java.io.*;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Task3 {

    private static final Logger logger = Logger.getLogger(Task3.class);
    private static ResourceBundle bundle;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose language / Sprache wahlen:\n1 - English\n2 - Deutsch");
        System.out.print("Choice / Wahl: ");
        String langChoice = scanner.nextLine();

        Locale locale;
        if (langChoice.equals("2")) {
            locale = new Locale("de", "DE");
        } else {
            locale = new Locale("en", "US");
        }

        bundle = ResourceBundle.getBundle("location.messages", locale);

        logger.info(bundle.getString("log.start"));

        String originalFile = "original.txt";
        String encryptedFile = "encrypted.txt";
        String decryptedFile = "decrypted.txt";

        char key = 'S';
        int keyCode = key;

        System.out.println(MessageFormat.format(bundle.getString("msg.key"), key, keyCode));

        try {
            logger.debug("Preparing to write original file...");

            try (Writer writer = new FileWriter(originalFile)) {
                writer.write("This is a test line.\nIt will be encrypted.");
                logger.info(MessageFormat.format(bundle.getString("log.file_write"), originalFile));
            }
            System.out.println(MessageFormat.format(bundle.getString("msg.original"), originalFile));
            System.out.println(bundle.getString("msg.content") + "\n" + readFile(originalFile) + "\n");

            System.out.println(bundle.getString("msg.encrypting"));
            try (Reader reader = new FileReader(originalFile);
                 Writer writer = new EncryptingWriter(new FileWriter(encryptedFile), keyCode)) {
                int c;
                while ((c = reader.read()) != -1) {
                    writer.write(c);
                }
            }
            System.out.println(MessageFormat.format(bundle.getString("msg.encrypted"), encryptedFile));
            System.out.println(bundle.getString("msg.content_hidden") + "\n" + readFile(encryptedFile) + "\n");

            System.out.println(bundle.getString("msg.decrypting"));
            try (Reader reader = new DecryptingReader(new FileReader(encryptedFile), keyCode);
                 Writer writer = new FileWriter(decryptedFile)) {
                int c;
                while ((c = reader.read()) != -1) {
                    writer.write(c);
                }
            }
            System.out.println(MessageFormat.format(bundle.getString("msg.decrypted"), decryptedFile));
            System.out.println(bundle.getString("msg.content_match") + "\n" + readFile(decryptedFile) + "\n");

            logger.info(bundle.getString("log.finish"));

        } catch (IOException e) {
            String errorMsg = MessageFormat.format(bundle.getString("err.io"), e.getMessage());
            System.err.println(errorMsg);
            logger.error("Exception occurred", e);
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
