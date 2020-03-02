package problems.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/binary-search-tree-iterator/
 */
public class BSTIterator {

    private static class Node {
        int val;
        Node left, right;

        Node(int val) {
            this.val = val;
        }
    }

    private Deque<Node> stack = new ArrayDeque<>();

    public BSTIterator(Node root) {
        addWithLeftChild(root);
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        Node next = stack.removeLast();
        addWithLeftChild(next.right);
        return next.val;
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return stack.size() > 0;
    }

    private void addWithLeftChild(Node node) {
        while (node != null) {
            stack.addLast(node);
            node = node.left;
        }
    }

}
