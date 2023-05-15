package problems.leetcode;


// https://leetcode.com/problems/maximum-depth-of-binary-tree
public class MaximumDepthOfBinaryTree {
    
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

    public int maxDepth(TreeNode root) {
        return root != null ? 1 + Math.max(maxDepth(root.left), maxDepth(root.right)) : 0;
    }

}
