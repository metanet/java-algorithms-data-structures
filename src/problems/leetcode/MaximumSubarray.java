package problems.leetcode;

/**
 * https://leetcode.com/problems/maximum-subarray/
 */
public class MaximumSubarray {

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));
    }

    public static int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int s = nums[i] + nums[i - 1];
            if (nums[i] < s) {
                nums[i] = s;
            }

            if (nums[i] > maxSum) {
                maxSum = nums[i];
            }
        }

        return maxSum;
    }

}
