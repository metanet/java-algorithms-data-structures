package problems.leetcode;

/**
 * https://leetcode.com/problems/maximum-binary-tree/
 */
public class MaximumBinaryTree {

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

    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTree(nums, 0, nums.length);
    }

    private static TreeNode constructMaximumBinaryTree(int[] nums, int i, int j) {
        if (i == j) {
            return null;
        }

        int k = maxIndex(nums, i, j);
        TreeNode node = new TreeNode(nums[k]);
        node.left = constructMaximumBinaryTree(nums, i, k);
        node.right = constructMaximumBinaryTree(nums, k + 1, j);

        return node;
    }

    private static int maxIndex(int[] nums, int i, int j) {
        int maxIndex = i;
        while (++i < j) {
            if (nums[maxIndex] < nums[i]) {
                maxIndex = i;
            }
        }

        return maxIndex;
    }


    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 6, 0, 5};
        TreeNode root = constructMaximumBinaryTree(nums);
        System.out.println();
    }

}
