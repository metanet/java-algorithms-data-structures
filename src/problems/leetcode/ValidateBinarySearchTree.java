package problems.leetcode;

/**
 * https://leetcode.com/problems/validate-binary-search-tree/submissions/
 */
public class ValidateBinarySearchTree {

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
    // space: O(H)
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        } else if ((min != null && root.val <= min) || (max != null && root.val >= max)) {
            return false;
        }

        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }

}
