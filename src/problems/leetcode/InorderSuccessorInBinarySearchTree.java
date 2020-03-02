package problems.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/inorder-successor-in-bst/
 */
public class InorderSuccessorInBinarySearchTree {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    '}';
        }
    }


    private Deque<TreeNode> stack = new ArrayDeque<>();

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }

        pushWithLeftChild(root);

        while (stack.size() > 0) {
            TreeNode next = stack.removeLast();
            pushWithLeftChild(next.right);
            if (next.val == p.val) {
                return stack.size() > 0 ? stack.removeLast() : null;
            }
        }

        return null;
    }

    private void pushWithLeftChild(TreeNode node) {
        while (node != null) {
            stack.addLast(node);
            node = node.left;
        }
    }

    private TreeNode target;

    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        target = p;
        return inorderSuccessor2(root);
    }

    private TreeNode inorderSuccessor2(TreeNode node) {
        if (node == null) {
            return null;
        }

        TreeNode successor = inorderSuccessor2(node.left);
        if (successor != null) {
            return successor;
        }

        if (target == null) {
            return node;
        }

        if (target == node) {
            target = null;
        }

        return inorderSuccessor2(node.right);
    }

}
