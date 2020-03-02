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

    public static boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        return isValidBST(root.left, Long.MIN_VALUE, root.val) && isValidBST(root.right, root.val, Long.MAX_VALUE);
    }

    private static boolean isValidBST(TreeNode node, long upperLimit, long lowerLimit) {
        return node == null || (node.val > upperLimit && node.val < lowerLimit
                && isValidBST(node.left, upperLimit, node.val)
                && isValidBST(node.right, node.val, lowerLimit));
    }

}
