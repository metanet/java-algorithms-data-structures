package problems.leetcode;

import java.util.List;

/**
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 */
public class KthSmallestElementInBST {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private int i;
    private int value;

    public int kthSmallest(TreeNode root, int k) {
        traverse(root, k);
        return value;
    }

    private void traverse(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        traverse(root.left, k);
        if (++i == k) {
            value = root.val;
            return;
        }
        traverse(root.right, k);
    }

}
