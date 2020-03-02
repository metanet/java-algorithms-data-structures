package problems.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/contains-duplicate/
 */
public class ContainsDuplicate {

    public static boolean containsDuplicate(int[] nums) {
        if (nums == null) {
            return false;
        }

        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }

        return false;
    }

}
