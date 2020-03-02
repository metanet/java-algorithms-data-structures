package problems.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/house-robber/
 * <p>
 * dynamic programming
 */
public class HouseRobber {

    private int[] totals;

    public int rob(int[] nums) {
        totals = new int[nums.length];
        Arrays.fill(totals, -1);
        return rob(nums, 0);
    }

    private int rob(int[] nums, int i) {
        if (i >= nums.length) {
            return 0;
        }

        if (totals[i] != -1) {
            return totals[i];
        }

        int without = rob(nums, i + 1);
        int with = nums[i] + rob(nums, i + 2);
        int total = Math.max(with, without);
        totals[i] = total;
        return total;
    }

    public int robDP(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] totals = new int[nums.length + 2];
        for (int i = nums.length - 1; i >= 0; i--) {
            totals[i] = Math.max(totals[i + 1], nums[i] + totals[i + 2]);
        }

        return totals[0];
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 9, 3, 5, 6};
        System.out.println(new HouseRobber().rob(nums));
        System.out.println(new HouseRobber().robDP(nums));
    }

}
