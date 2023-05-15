package problems.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversals {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "TreeNode{" + "val=" + val + '}';
        }
    }

    // runtime: O(N)
    // space: O(N)
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inorderIndices = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndices.put(inorder[i], i);
        }
        return buildTree(preorder, inorder, inorderIndices, 0, preorder.length, 0, inorder.length);
    }

    private static TreeNode buildTree(int[] preorder, int[] inorder, Map<Integer, Integer> inorderIndices, int preStart,
            int preEnd, int inStart, int inEnd) {
        if (preStart == preEnd) {
            return null;
        }

        int val = preorder[preStart];
        TreeNode node = new TreeNode(val);
        int diff = inorderIndices.get(val) - inStart;
        if (diff > 0) {
            node.left = buildTree(preorder, inorder, inorderIndices, 1 + preStart, 1 + preStart + diff, inStart,
                    inStart + diff);
        }
        node.right = buildTree(preorder, inorder, inorderIndices, 1 + preStart + diff, preEnd, 1 + inStart + diff,
                inEnd);

        return node;
    }

    public static void main(String[] args) {
        // TreeNode root = buildTree(new int[]{1, 3}, new int[]{1, 3});
        // TreeNode root = buildTree(new int[]{1, 2}, new int[]{2, 1});
        // TreeNode root = buildTree(new int[]{1, 2, 3}, new int[]{2, 1, 3});
        // TreeNode root = buildTree(new int[]{1, 2, 3}, new int[]{3, 2, 1});
        // TreeNode root = buildTree(new int[]{1, 2, 3, 4}, new int[]{3, 2, 4, 1});
        // TreeNode root = buildTree(new int[]{1, 2, 3}, new int[]{2, 3, 1});
        // TreeNode root = buildTree(new int[]{1, 2, 3}, new int[]{1, 3, 2});
        // TreeNode root = buildTree(new int[]{1, 2, 3}, new int[]{1, 2, 3});
        // TreeNode root = buildTree(new int[]{1, 2, 3, 4}, new int[]{1, 3, 2, 4});
        TreeNode root = buildTree(new int[] { 3, 9, 20, 15, 7 }, new int[] { 9, 3, 15, 20, 7 });
        System.out.println();
    }
}
