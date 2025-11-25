package org.example;

public class ConsoleView {

    public void printMenu() {
        System.out.println("\n--- Red-Black Tree Menu ---");
        System.out.println("1. Fill with random numbers");
        System.out.println("2. Fill with ordered numbers (stress test)");
        System.out.println("3. Add a number from keyboard");
        System.out.println("4. Display tree");
        System.out.println("5. Print in-order traversal");
        System.out.println("6. Reset (clear) tree");
        System.out.println("0. Exit");
    }
    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(String error) {
        System.out.println("Error: " + error);
    }

    public void printTree(RedBlackTree tree) {
        if (tree.getRoot() == tree.getNIL()) {
            System.out.println("Tree is empty.");
            return;
        }
        printRecursive(tree.getRoot(), tree.getNIL(), "", true);
    }

    private void printRecursive(Node node, Node nil, String prefix, boolean isRightChild) {
        if (node != nil) {
            System.out.print(prefix);
            if (isRightChild) {
                System.out.print("R----");
                prefix += "     ";
            } else {
                System.out.print("L----");
                prefix += "|    ";
            }
            String colorCode = (node.color == Color.RED) ? "(RED)" : "(BLK)";
            System.out.println(node.data + " " + colorCode);

            printRecursive(node.left, nil, prefix, false);
            printRecursive(node.right, nil, prefix, true);
        }
    }

    public void printInOrder(RedBlackTree tree) {
        System.out.print("In-Order (sorted) traversal: ");
        Node root = tree.getRoot();
        Node nil = tree.getNIL();

        if (root == nil) {
            System.out.print("(empty)");
        } else {
            printInOrderHelper(root, nil);
        }
        System.out.println();
    }

    private void printInOrderHelper(Node node, Node nil) {
        if (node != nil) {
            printInOrderHelper(node.left, nil);
            System.out.print(node.data + " ");
            printInOrderHelper(node.right, nil);
        }
    }
}