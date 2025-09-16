package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class Main {
    static Predicate<String> areLettersAscending = word ->
            IntStream.range(0, word.length() - 1)
                    .allMatch(i -> word.charAt(i) < word.charAt(i + 1));
    public static String[] splitLineToWords(String line){
        return line.isBlank() ? new String[0] : line.split("\\s+");
    }
    public static List<String> handleLine(String line) {
        return Arrays.stream(splitLineToWords(line)).filter(areLettersAscending).toList();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        handleLine(line).forEach(word -> System.out.print(word + "\t"));
    }
}