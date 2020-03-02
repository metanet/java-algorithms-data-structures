package problems.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/range-sum-query-mutable/
 */
public class RangeSumQueryMutable {
    int[] nums, sums;

    public RangeSumQueryMutable(int[] nums) {
        this.nums = Arrays.copyOf(nums, nums.length);
        this.sums = Arrays.copyOf(nums, nums.length);
        for (int i = 1; i < nums.length; i++) {
            this.sums[i] += this.sums[i - 1];
        }
    }

    public void update(int i, int val) {
        int diff = val - nums[i];
        nums[i] = val;
        for (; i < nums.length; i++) {
            sums[i] += diff;
        }
    }

    public int sumRange(int i, int j) {
        return sums[j] - (i > 0 ? sums[i - 1] : 0);
    }

    public static void main(String[] args) {
        RangeSumQueryMutable array = new RangeSumQueryMutable(new int[]{1, 3, 5});
        System.out.println(array.sumRange(0, 2));
        array.update(2, 3);
        System.out.println(array.sumRange(0, 2));
    }

}
