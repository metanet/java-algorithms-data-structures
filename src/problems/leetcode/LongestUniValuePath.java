package problems.leetcode;

// https://leetcode.com/problems/longest-univalue-path
public class LongestUniValuePath {

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
    
    private int longestPath = 0;

    public int longestUnivaluePath(TreeNode root) {
        traverse(root);
        return longestPath;
    }

    // traverse(root = 1)
    // left
    public int traverse(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = traverse(root.left);
        int right = traverse(root.right);
        int uniqueLeft = 0, uniqueRight = 0;
        if (root.left != null && root.val == root.left.val) {
            uniqueLeft = left + 1;
        }

        if (root.right != null && root.val == root.right.val) {
            uniqueRight = right + 1;
        }

        longestPath = Math.max(longestPath, uniqueLeft + uniqueRight);
        return Math.max(uniqueLeft, uniqueRight);
    }

}
