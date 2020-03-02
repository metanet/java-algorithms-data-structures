package problems.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 */
public class PopulatingNextRightPointersInEachNode {

    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    private List<Node> prevs = new ArrayList<>();

    public Node connect(Node root) {
        connect(root, 1);
        return root;
    }

    private void connect(Node node, int level) {
        if (node == null) {
            return;
        }

        if (prevs.size() < level) {
            prevs.add(node);
        } else {
            Node prev = prevs.get(level - 1);
            prev.next = node;
            prevs.set(level - 1, node);
        }

        connect(node.left, level + 1);
        connect(node.right, level + 1);
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        Node newRoot = new PopulatingNextRightPointersInEachNode().connect(root);
        System.out.println();
    }

}
