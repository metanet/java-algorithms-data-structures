package problems.leetcode;

/**
 * https://leetcode.com/problems/recover-binary-search-tree/
 */
public class RecoverBinarySearchTree {

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

    TreeNode x = null, y = null, pred = null;

    public void recoverTree(TreeNode root) {
        findTwoSwapped(root);
        swap(x, y);
    }

    public void findTwoSwapped(TreeNode node) {
        if (node == null) {
            return;
        }

        findTwoSwapped(node.left);

        if (pred != null && node.val < pred.val) {
            y = node;
            if (x == null) {
                x = pred;
            } else {
                return;
            }
        }

        pred = node;
        findTwoSwapped(node.right);
    }

    private static void swap(TreeNode node1, TreeNode node2) {
        int tmp = node1.val;
        node1.val = node2.val;
        node2.val = tmp;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(1);
//        root.right.left.left = new TreeNode(6);

        new RecoverBinarySearchTree().recoverTree(root);

        System.out.println();
    }

}
