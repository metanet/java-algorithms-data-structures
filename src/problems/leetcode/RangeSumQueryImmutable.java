package problems.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/range-sum-query-immutable
 */
public class RangeSumQueryImmutable {
    int[] sums;

    public RangeSumQueryImmutable(int[] nums) {
        this.sums = Arrays.copyOf(nums, nums.length);
        for (int i = 1; i < sums.length; i++) {
            sums[i] += sums[i - 1];
        }
    }

    public int sumRange(int i, int j) {
        return sums[j] - (i > 0 ? sums[i - 1] : 0);
    }
}
