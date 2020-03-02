package problems.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/majority-element/
 */
public class MajorityElement {

    public static void main(String[] args) {
//        int[] nums = {2, 1, 2};
        int[] nums = {2, 3, 3, 4};
        System.out.println(majorityElement(nums));
    }

    public static int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
        // 1, 2, 2

        // 2, 3, 3, 4
    }

}
