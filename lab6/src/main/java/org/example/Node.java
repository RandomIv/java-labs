package org.example;

public class Node {
    int data;
    Color color;
    Node parent;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
        this.color = Color.RED;
    }
}
