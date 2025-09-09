package org.example;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static Boolean areLettersAscending(String word) {
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length-1; i++) {
            if (chars[i] >= chars[i + 1]) return false;
        }
        return true;
    }
    public static String[] splitLineToWords(String line){
        if (line.isBlank()) {
            return new String[0];
        }
        return line.split("\\s+");
    }
    public static ArrayList<String> handleLine(String line) {
        ArrayList<String> result = new ArrayList<>();
        String[] words = splitLineToWords(line);
        for(String word : words){
            if(areLettersAscending(word)){
                result.add(word);
            }
        }
        return result;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        ArrayList<String> result  = handleLine(line);
        for(String word : result) {
            System.out.print(word + '\t');
        }
    }
}