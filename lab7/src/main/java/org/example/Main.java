package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    static final Predicate<String> areLettersAscending = word ->
            IntStream.range(0, word.length() - 1)
                    .allMatch(i -> word.charAt(i) < word.charAt(i + 1));

    static final Function<String, Stream<String>> splitLineToStream = line ->
            line.isBlank() ? Stream.empty() : Arrays.stream(line.split("\\s+"));

    static final Function<String, List<String>> processLine =
            splitLineToStream.andThen(stream -> stream.filter(areLettersAscending).toList());

    static final Supplier<String> lineSupplier = new Scanner(System.in)::nextLine;

    static final Consumer<List<String>> printWords = list ->
            list.forEach(word -> System.out.print(word + "\t"));


    public static void main(String[] args) {
        printWords.accept(processLine.apply(lineSupplier.get()));
    }
}