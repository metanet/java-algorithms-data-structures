package problems.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/submissions/
 */
public class PopulatingNextRightPointersInEachNodeII {

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

    // runtime: O(N)
    // space: O(N/2) which is actually O(N). N/2 is the number of nodes in the leaf level.
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        List<Node> nodes = new ArrayList<>();
        nodes.add(root);

        while (nodes.size() > 0) {
            Node prev = null;
            List<Node> nextLevel = new ArrayList<>();
            for (Node node : nodes) {
                if (node.left != null) {
                    nextLevel.add(node.left);
                }
                if (node.right != null) {
                    nextLevel.add(node.right);
                }
                if (prev != null) {
                    prev.next = node;
                } 
                prev = node;
            }
            nodes = nextLevel;
        }

        return root;        
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
