package problems.leetcode;

/**
 * https://leetcode.com/problems/check-completeness-of-a-binary-tree/
 */
public class CheckCompletenessOfBinaryTree {

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

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        root1.right.left = new TreeNode(6);

        System.out.println(new CheckCompletenessOfBinaryTree().isCompleteTree(root1));

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(5);
        root2.right.right = new TreeNode(7);

        System.out.println(new CheckCompletenessOfBinaryTree().isCompleteTree(root2));
    }

    private int count, sum;

    public boolean isCompleteTree(TreeNode root) {
        traverse(root, 1);
        return sum == count * (count + 1) / 2;
    }

    private void traverse(TreeNode node, int index) {
        if (node == null) {
            return;
        }

        count++;
        sum += index;
        traverse(node.left, index * 2);
        traverse(node.right, index * 2 + 1);
    }

}
