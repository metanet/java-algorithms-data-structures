package problems.leetcode;

/**
 * https://leetcode.com/problems/maximum-subarray/
 */
public class MaximumSubarray {

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));
    }

    // runtime: O(N)
    // space: O(1)
    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] = Math.max(nums[i], nums[i] + nums[i - 1]);
            maxSum = Math.max(maxSum, nums[i]);
        }

        return maxSum;
    }

}
