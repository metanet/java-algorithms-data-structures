package problems.leetcode;

/**
 * https://leetcode.com/problems/merge-two-binary-trees/
 */
public class MergeTwoBinaryTrees {

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

    public static TreeNode mergeTrees(TreeNode node1, TreeNode node2) {
        if (node1 == null) {
            return node2;
        } else if (node2 == null) {
            return node1;
        }

        TreeNode merged = new TreeNode(node1.val + node2.val);
        merged.left = mergeTrees(node1.left, node2.left);
        merged.right = mergeTrees(node1.right, node2.right);

        return merged;
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(5);

        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(3);
        root2.left.right = new TreeNode(4);
        root2.right.right = new TreeNode(7);

        TreeNode merged = mergeTrees(root1, root2);
        System.out.println(merged);
    }

}
