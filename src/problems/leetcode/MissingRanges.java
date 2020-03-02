package problems.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/missing-ranges/
 */
public class MissingRanges {

    public static void main(String[] args) {
        int[] nums = {-2147483648, -2147483648, 0, 2147483647, 2147483647};
        int lower = -2147483648;
        int upper = 2147483647;

        List<String> missingRanges = findMissingRanges(nums, lower, upper);
        System.out.println(missingRanges);
    }

    public static List<String> findMissingRanges(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return Collections.singletonList(range(lower, upper));
        }

        int n = nums.length;
        List<String> missing = new ArrayList<>();

        if (nums[0] > Integer.MIN_VALUE) {
            addRange(lower, nums[0] - 1, missing);
        }

        for (int i = 1; i < n; i++) {
            if (nums[i - 1] < Integer.MAX_VALUE && nums[i] > Integer.MIN_VALUE) {
                addRange(nums[i - 1] + 1, nums[i] - 1, missing);
            }
        }

        if (nums[n - 1] < Integer.MAX_VALUE) {
            addRange(nums[n - 1] + 1, upper, missing);
        }

        return missing;
    }

    private static void addRange(int lower, int upper, List<String> ranges) {
        String range = range(lower, upper);
        if (range != null) {
            ranges.add(range);
        }
    }

    private static String range(int lower, int upper) {
        if (lower > upper) {
            return null;
        }

        return lower == upper ? String.valueOf(lower) : lower + "->" + upper;
    }

}
