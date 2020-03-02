package problems.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/longest-increasing-subsequence/
 */
public class LongestIncreasingSubsequence {

    private int[] memo;

    public int lengthOfLIS(int[] nums) {
        if (nums == null) {
            return 0;
        } else if (nums.length < 2) {
            return nums.length;
        }

        memo = new int[nums.length];
        Arrays.fill(memo, -1);

        int longestLen = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            longestLen = Math.max(longestLen, lengthOfLIS(nums, i));
        }

        return longestLen;
    }

    private int lengthOfLIS(int[] nums, int i) {
        if (i == nums.length - 1) {
            return 1;
        } else if (memo[i] != -1) {
            return memo[i];
        }

        int num = nums[i];
        int longestLen = 1;
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[j] > num) {
                longestLen = Math.max(longestLen, 1 + lengthOfLIS(nums, j));
            }
        }

        memo[i] = longestLen;

        return longestLen;
    }

    public int lengthOfLISDP(int[] nums) {
        if (nums == null) {
            return 0;
        } else if (nums.length < 2) {
            return nums.length;
        }

        int longestLen = 0;
        int[] dp = new int[nums.length];
        dp[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            int l = 1;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] < nums[j]) {
                    l = Math.max(l, 1 + dp[j]);
                }
            }

            dp[i] = l;
            longestLen = Math.max(longestLen, l);
        }

        return longestLen;
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
//        int[] nums = {4, 10, 4, 3, 8, 9};
        System.out.println(new LongestIncreasingSubsequence().lengthOfLIS(nums));
        System.out.println(new LongestIncreasingSubsequence().lengthOfLISDP(nums));
    }

    /*
    Input: [10,9,2,5,3,7,101,18]
    Output: 4
    Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

    the algorithm must run in O(n^2)




     */

}
