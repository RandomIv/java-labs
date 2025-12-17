package org.example;

public class RedBlackTree {
    private Node root;
    private final Node NIL;

    public RedBlackTree() {
        NIL = new Node(0);
        NIL.color = Color.BLACK;
        NIL.left = NIL;
        NIL.right = NIL;
        NIL.parent = NIL;
        root = NIL;
    }

    public void add(int data) {
        Node newNode = new Node(data);
        newNode.left = NIL;
        newNode.right = NIL;
        newNode.color = Color.RED;
        Node parent = NIL;
        Node current = root;

        while (current != NIL) {
            parent = current;
            current = (data < current.data) ? current.left : current.right;
        }

        newNode.parent = parent;
        if (parent == NIL) {
            root = newNode;
        } else if (data < parent.data) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        addFixup(newNode);
    }

    private void addFixup(Node node) {
        while (node.parent.color == Color.RED) {
            Node grandParent = node.parent.parent;
            boolean isParentLeft = (node.parent == grandParent.left);

            Node uncle = isParentLeft ? grandParent.right : grandParent.left;

            if (uncle.color == Color.RED) {
                node.parent.color = Color.BLACK;
                uncle.color = Color.BLACK;
                grandParent.color = Color.RED;
                node = grandParent;
            }
            else {
                //Triangle formation
                if (node == (isParentLeft ? node.parent.right : node.parent.left)) {
                    node = node.parent;
                    rotate(node, isParentLeft);
                }

                //Line formation
                node.parent.color = Color.BLACK;
                grandParent.color = Color.RED;
                rotate(grandParent, !isParentLeft);
            }

            if (node == root) {
                break;
            }
        }
        root.color = Color.BLACK;
    }
    private void rotate(Node node, boolean directionLeft) {
        if (directionLeft) {
            leftRotate(node);
        } else {
            rightRotate(node);
        }
    }
    private void leftRotate(Node node) {
        Node childNode = node.right;
        node.right = childNode.left;
        if (childNode.left != NIL) {
            childNode.left.parent = node;
        }
        childNode.parent = node.parent;
        if (node.parent == NIL) {
            this.root = childNode;
        } else if (node == node.parent.left) {
            node.parent.left = childNode;
        } else {
            node.parent.right = childNode;
        }
        childNode.left = node;
        node.parent = childNode;
    }

    private void rightRotate(Node node) {
        Node childNode = node.left;
        node.left = childNode.right;
        if (childNode.right != NIL) {
            childNode.right.parent = node;
        }
        childNode.parent = node.parent;
        if (node.parent == NIL) {
            this.root = childNode;
        } else if (node == node.parent.right) {
            node.parent.right = childNode;
        } else {
            node.parent.left = childNode;
        }
        childNode.right = node;
        node.parent = childNode;
    }

    public Node findNode(int data) {
        Node current = root;
        while (current != NIL) {
            if (data == current.data) {
                return current;
            }
            current = (data < current.data) ? current.left : current.right;
        }
        return NIL;
    }

    public Node getRoot() {
        return this.root;
    }

    public Node getNIL() {
        return this.NIL;
    }
}