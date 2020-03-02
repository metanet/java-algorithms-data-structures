package problems.leetcode;

public class MaxSumOfNonAdjacentSubArray {

    public static int maxSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else if (nums.length < 2) {
            return nums[0];
        }

        int n = nums.length;
        int[] sums = new int[n];
        sums[n - 1] = nums[n - 1];
        sums[n - 2] = Math.max(nums[n - 2], nums[n - 1]);

        for (int i = n - 3; i >= 0; i--) {
            sums[i] = Math.max(nums[i] + sums[i + 2], sums[i + 1]);
        }

        return sums[0];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 5, 2, 3, 4, 7, 6};

        System.out.println(maxSum(nums));
    }
}
