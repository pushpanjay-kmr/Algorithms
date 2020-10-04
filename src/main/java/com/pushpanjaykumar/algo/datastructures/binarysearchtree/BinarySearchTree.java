package main.java.com.pushpanjaykumar.algo.datastructures.binarysearchtree;

import java.util.Iterator;

/**
 * @author pushpanjay.kumar
 */
public class BinarySearchTree<T extends Comparable<T>> {
    private Node root = null;

    private int nodeCount = 0;

    public BinarySearchTree(T elem) {
        this.root = new Node(elem, null, null);
        this.nodeCount++;
    }

    public int size() {
        return nodeCount;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(T elem) {
        Node temp = root;
        while (temp != null) {
            if (elem.compareTo(temp.data) == 0) {
                return true;
            } else if (elem.compareTo(temp.data) < 0) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }
        return false;
    }

    /**
     * Method to add element to the BST
     *
     * @param elem value to be added to BST
     * @return true if successfully added
     */
    public boolean add(T elem) {
        if (elem == null) {
            throw new IllegalArgumentException();
        }

        if (contains(elem)) {
            return false;
        } else {
            root = add(root, elem);
            nodeCount++;
            return true;
        }
    }

    private Node add(Node node, T elem) {
        if (node == null) {
            node = new Node(elem, null, null);
        } else {
            if (elem.compareTo(node.data) < 0) {
                node.left = add(node.left, elem);
            } else {
                node.right = add(node.right, elem);
            }
        }
        return node;
    }

    /**
     * Method to remove an element from the BST
     *
     * @param elem the element to be removed
     * @return true if element is successfully removed
     */
    public boolean remove(T elem) {
        if (elem == null) {
            throw new IllegalArgumentException();
        }
        if (contains(elem)) {
            root = remove(root, elem);
            nodeCount--;
            return true;
        }
        return false;
    }

    private Node remove(Node node, T elem) {
        int cmp = elem.compareTo(node.data);
        if (cmp < 0) {
            node.left = remove(node.left, elem);
        } else if (cmp > 0) {
            node.right = remove(node.right, elem);
        } else {

            if (node.left == null) {
                Node rightChild = node.right;
                node.data = null;
                node = null;
                return rightChild;
            } else if (node.right == null) {
                Node leftChild = node.left;
                node.data = null;
                node = null;
                return leftChild;

            } else { //This case will come when node to be deleted has both child
                /*
                 We have 2 options for successor node
                 1. Smallest in right subtree
                 2. largest in left subtree
                 */
                //Lets go with option 1
                Node successor = digLeft(node.right);
                node.data = successor.data;

                node.right = remove(node.right, successor.data);
            }
        }
        return null;
    }

    private Node digLeft(Node node){
        Node cur = node;
        while (cur.left!=null){
            cur = cur.left;
        }
        return cur;
    }

    private Node digRight(Node node){
        Node cur = node;
        while (cur.right!=null){
            cur = cur.right;
        }
        return cur;
    }

    public int height(){
        return height(root);
    }

    private int height(Node node){
        if(node == null){
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public Iterator<T> traverse(TreeTraversalOrder order){
        switch (order){
            case PRE_ORDER: return preOrderTraversal();
            case IN_ORDER: return inOrderTraversal();
            case POST_ORDER: return postOrderTraversal();
            case LEVEL_ORDER: return levelOrderTraversal();
            default: return null;
        }
    }

    private Iterator<T> levelOrderTraversal() {
        //todo
        return null;
    }

    private Iterator<T> postOrderTraversal() {
        //todo
        return null;
    }

    private Iterator<T> inOrderTraversal() {
        //todo
        return null;
    }

    private Iterator<T> preOrderTraversal() {
        //todo
        return null;
    }

    private class Node {
        T data;
        Node left, right;

        public Node(T elem, Node next, Node prev) {
            this.data = elem;
            this.left = next;
            this.right = prev;
        }
    }
}
