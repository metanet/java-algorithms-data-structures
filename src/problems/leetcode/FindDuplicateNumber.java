package problems.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/find-the-duplicate-number/
 */
public class FindDuplicateNumber {

    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 2, 3};
        System.out.println(findDuplicate(nums));
        System.out.println(Arrays.toString(nums));
    }

    public static int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;

        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) {
                break;
            }
        }

        fast = 0;

        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }

    /*

    1,2,2,2,4

     */

}
