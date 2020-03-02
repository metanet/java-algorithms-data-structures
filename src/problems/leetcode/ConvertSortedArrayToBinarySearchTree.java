package problems.leetcode;

/**
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 */
public class ConvertSortedArrayToBinarySearchTree {

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


    public static TreeNode sortedArrayToBST(int[] nums) {
        return nums != null ? sortedArrayToBST(nums, 0, nums.length) : null;
    }

    // start: inclusive, end: exclusive
    private static TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (start >= end) {
            return null;
        } else if (start == end - 1) {
            return new TreeNode(nums[start]);
        }

        int mid = start + (end - start) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = sortedArrayToBST(nums, start, mid);
        node.right = sortedArrayToBST(nums, mid + 1, end);

        return node;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 2};
        TreeNode root = sortedArrayToBST(nums);
        System.out.println(root);
    }

    /*

    0,1,2,3,4,5,6,7,8,9


               4
            /     \
           1       7
          / \     / \
         0   3   6   9
            /   /   /
           2   5   8
     */

}
