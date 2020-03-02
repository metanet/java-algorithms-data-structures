package problems.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 */
public class BinaryTreeInorderTraversal {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> vals = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        pushWithLeftChildren(stack, node);

        while (stack.size() > 0) {
            node = stack.removeLast();
            pushWithLeftChildren(stack, node.right);
            vals.add(node.val);
        }

        return vals;
    }

    private static void pushWithLeftChildren(Deque<TreeNode> stack, TreeNode node) {
        while (node != null) {
            stack.addLast(node);
            node = node.left;
        }
    }

}
