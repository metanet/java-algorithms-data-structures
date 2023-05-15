package problems.leetcode;

// https://leetcode.com/problems/minimum-depth-of-binary-tree/
public class MinDepthOfBinaryTree {
    
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    private int minDepth = Integer.MAX_VALUE;
    
    public int minDepth(TreeNode root) {
        traverse(root, 1);
        return minDepth != Integer.MAX_VALUE ? minDepth : 0;
    }

    private void traverse(TreeNode root, int h) {
        if (root == null || h > minDepth) {
            return;
        } else if (root.left == null && root.right == null) {
            minDepth = Math.min(minDepth, h);
        }

        traverse(root.left, h + 1);
        traverse(root.right, h + 1);
    }

}
