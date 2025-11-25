package org.example;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Application {
    private RedBlackTree tree;
    private final ConsoleView view;
    private final Random random;
    private final Scanner scanner;

    public Application() {
        this.tree = new RedBlackTree();
        this.view = new ConsoleView();
        this.random = new Random();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            view.printMenu();
            int choice = getInt("Your choice: ");

            switch (choice) {
                case 1:
                    handleAddRandom();
                    break;
                case 2:
                    handleAddOrdered();
                    break;
                case 3:
                    handleAddFromKeyboard();
                    break;
                case 4:
                    view.printTree(tree);
                    break;
                case 5:
                    view.printInOrder(tree);
                    break;
                case 6:
                    handleResetTree();
                    break;
                case 0:
                    view.showMessage("Exiting...");
                    scanner.close();
                    return;
                default:
                    view.showError("Invalid choice. Please try again.");
            }
            view.showMessage("------------------------------------");
        }
    }

    private void handleAddRandom() {
        int count = getInt("How many random numbers to add? ");
        int[] randomArray = new int[count];
        for (int i = 0; i < count; i++) {
            randomArray[i] = random.nextInt(100);
        }

        view.showMessage("Adding numbers in order: " + Arrays.toString(randomArray));
        for (int num : randomArray) {
            tree.add(num);
        }
        view.printTree(tree);
    }

    private void handleAddOrdered() {
        int sortedCount = getInt("How many ordered numbers to add? ");
        int[] sortedArray = new int[sortedCount];
        int start = random.nextInt(50);
        for (int i = 0; i < sortedCount; i++) {
            sortedArray[i] = start + i;
        }

        view.showMessage("Adding numbers in order: " + Arrays.toString(sortedArray));
        view.showMessage("This is a stress test for rotations. The tree will be balanced.");
        for (int num : sortedArray) {
            tree.add(num);
        }
        view.printTree(tree);
    }

    private void handleAddFromKeyboard() {
        int numToAdd = getInt("Enter a number to add: ");
        tree.add(numToAdd);
        view.showMessage("Tree after adding " + numToAdd + ":");
        view.printTree(tree);
    }

    private void handleResetTree() {
        tree = new RedBlackTree();
        view.showMessage("Tree has been reset (cleared).");
    }

    private int getInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            view.showError("Please enter a valid number!");
            scanner.next();
            System.out.print(prompt);
        }
        return scanner.nextInt();
    }
}