package org.example.task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Task1 {

    public static void main(String[] args) {
        String filePath = "input.txt";
        String lineWithMaxWords = "";
        int maxWordCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if (trimmedLine.isEmpty()) {
                    continue;
                }

                String[] words = trimmedLine.split("\\s+");
                int currentWordCount = words.length;

                if (currentWordCount > maxWordCount) {
                    maxWordCount = currentWordCount;
                    lineWithMaxWords = currentLine;
                }
            }

            System.out.println("File: " + filePath);
            if (maxWordCount > 0) {
                System.out.println("Line with the maximum number of words (" + maxWordCount + "):");
                System.out.println(lineWithMaxWords);
            } else {
                System.out.println("The file is empty or contains no words.");
            }

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
