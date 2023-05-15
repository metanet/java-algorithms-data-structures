package problems.leetcode;

// https://leetcode.com/problems/path-sum
public class PathSum {
    
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        } 

        targetSum -= root.val;
        if (targetSum == 0 && root.left == null && root.right == null) {
            return true;
        }
        
        return hasPathSum(root.left, targetSum) || hasPathSum(root.right, targetSum);
    }

}
