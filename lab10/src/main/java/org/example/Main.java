package org.example;

import java.lang.reflect.Field;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String literalString = "Java";

        System.out.print("Enter any string: ");
        String inputString = scanner.nextLine();

        System.out.println("\n--- Before Change ---");
        System.out.println("Literal: " + literalString);
        System.out.println("Input: " + inputString);

        System.out.print("\nEnter new value to inject: ");
        String replacement = scanner.nextLine();

        try {
            Field valueField = String.class.getDeclaredField("value");
            valueField.setAccessible(true);

            Object newValue;
            if (valueField.getType() == byte[].class) {
                newValue = replacement.getBytes();
            } else {
                newValue = replacement.toCharArray();
            }

            valueField.set(literalString, newValue);
            valueField.set(inputString, newValue);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println("\n--- After Reflection Magic ---");
        System.out.println("Literal variable: " + literalString);
        System.out.println("Input variable: " + inputString);
        System.out.println("Checking string literal 'Java': " + "Java");
    }
}