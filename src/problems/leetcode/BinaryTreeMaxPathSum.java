package problems.leetcode;

/**
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 */
public class BinaryTreeMaxPathSum {

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

    private static int maxSum = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        traverse(root);
        return maxSum;
    }

    private static int traverse(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftSum = traverse(node.left), rightSum = traverse(node.right);
        // this node only, this node with left sum, this node with right sum
        int maxChildSum = Math.max(node.val, node.val + Math.max(leftSum, rightSum));
        maxSum = Math.max(maxChildSum, maxSum);
        // sum when this node is root
        maxSum = Math.max(node.val + leftSum + rightSum, maxSum);

        return maxChildSum;
    }

    /*
   -10
   /   \
  9    -1
 /    /  \
30   20  50
    /  \
   60   1
     *
     *
     */
    public static void main(String[] args) {
        TreeNode n_10 = new TreeNode(-10);
        TreeNode n9 = new TreeNode(9);
        TreeNode n_1 = new TreeNode(-1);
        TreeNode n30 = new TreeNode(30);
        TreeNode n20 = new TreeNode(20);
        TreeNode n50 = new TreeNode(50);
        TreeNode n60 = new TreeNode(60);
        TreeNode n1 = new TreeNode(1);

        n_10.left = n9;
        n_10.right = n_1;
        n9.left = n30;
        n_1.left = n20;
        n_1.right = n50;
        n20.left = n60;
        n20.right = n1;

        System.out.println(maxPathSum(n_10));

    }

}
