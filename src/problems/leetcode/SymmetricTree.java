package problems.leetcode;

/**
 * https://leetcode.com/problems/symmetric-tree/
 */
public class SymmetricTree {

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
    public static boolean isSymmetric(TreeNode root) {
        return root == null || isSameTree(root.left, root.right);
    }

    private static boolean isSameTree(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        } else if (node1 == null || node2 == null || node1.val != node2.val) {
            return false;
        }

        return isSameTree(node1.right, node2.left) && isSameTree(node1.left, node2.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

        boolean symmetric = isSymmetric(root);
        System.out.println(symmetric);
    }

}
