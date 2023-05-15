package problems.leetcode;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
public class ConstructBinaryTreeFromInorderAndPostorderTraversals {

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

    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inorderIndices = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndices.put(inorder[i], i);
        }
        return buildTree(inorder, postorder, inorderIndices, 0, postorder.length - 1, 0, inorder.length - 1);
    }

    private static TreeNode buildTree(int[] inorder, int[] postorder, Map<Integer, Integer> inorderIndices, int inStart,
            int inEnd, int postStart, int postEnd) {
        if (postStart > postEnd) {
            return null;
        }

        int val = postorder[postEnd];
        TreeNode node = new TreeNode(val);
        int diff = inEnd - inorderIndices.get(val);
        if (diff > 0) {
            node.right = buildTree(inorder, postorder, inorderIndices, inEnd - diff + 1,
                    inEnd, postEnd - diff, postEnd - 1);
        }
        node.left = buildTree(inorder, postorder, inorderIndices, inStart,
                inEnd - diff - 1, postStart, postEnd - diff - 1);

        return node;
    }

    public static void main(String[] args) {
        // TreeNode root = new TreeNode(3);
        // root.left = new TreeNode(9);
        // root.right = new TreeNode(20);
        // root.right.left = new TreeNode(15);
        // root.right.right = new TreeNode(7);

        TreeNode root = buildTree(new int[] {9, 6, 3, 15, 20, 7}, new int[]{6, 9, 15, 7, 20, 3});
        System.out.println();
    }

}
