package problems.leetcode;

/**
 * https://leetcode.com/problems/same-tree/
 */
public class SameTree {

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

    public boolean isSameTree(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        } else if (node1 != null && node2 == null) {
            return false;
        } else if (node1 == null) {
            return false;
        } else if (node1.val != node2.val) {
            return false;
        }

        return isSameTree(node1.left, node2.left) && isSameTree(node1.right, node2.right);
    }

}
