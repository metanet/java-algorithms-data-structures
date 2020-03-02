package problems.leetcode;

/**
 * https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
 */
public class ConvertBinarySearchTreeToSortedDoublyLinkedList {

    private static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" + "val=" + val + '}';
        }
    }

    private Node head, tail;

    public Node treeToDoublyList(Node node) {
        if (node == null) {
            return null;
        }

        traverse(node);

        tail.right = head;
        head.left = tail;

        return head;
    }

    private void traverse(Node node) {
        if (node == null) {
            return;
        }

        traverse(node.left);

        if (head == null) {
            head = node;
        }

        if (tail != null) {
            node.left = tail;
            tail.right = node;
        }

        tail = node;

        traverse(node.right);
    }

    public static void main(String[] args) {
        Node root = new Node(4);
        root.left = new Node(2);
        root.left.left = new Node(1);
        root.left.right = new Node(3);
        root.right = new Node(5);

        Node head = new ConvertBinarySearchTreeToSortedDoublyLinkedList().treeToDoublyList(root);

        System.out.println();

    }

}
