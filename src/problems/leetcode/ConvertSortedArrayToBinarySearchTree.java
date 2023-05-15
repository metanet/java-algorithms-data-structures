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
        if (nums == null || nums.length < 1) {
            return null;
        }

        return convert(nums, 0, nums.length - 1);
    }

    private static TreeNode convert(int[] nums, int l, int r) {
        if (l > r) {
            return null;
        }

        int m = (l + r) / 2;
        TreeNode root = new TreeNode(nums[m]);
        root.left = convert(nums, l, m - 1);
        root.right = convert(nums, m + 1, r);

        return root;
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
