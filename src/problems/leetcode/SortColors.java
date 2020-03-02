package problems.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/sort-colors/
 * <p>
 * the dutch flag problem
 */
public class SortColors {

    public static void main(String[] args) {
        int[] nums = {1, 0, 2, 1, 0, 0};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void sortColors(int[] nums) {
        // for all idx < i : nums[idx < i] = 0
        // for all idx > k : nums[idx > k] = 2
        int p0 = 0, p2 = nums.length - 1, i = 0;

        while (i <= p2) {
            if (nums[i] == 0) {
                // swap p0-th and i-th elements
                // i++ and j++
                swap(nums, p0++, i++);
            } else if (nums[i] == 2) {
                // swap k-th and i-th elements
                // p2--
                swap(nums, i, p2--);
            } else {
                i++;
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int n = nums[i];
        nums[i] = nums[j];
        nums[j] = n;
    }

    /*

    2,0,2,1,1,0
    0,0,2,1,1,2
    0,0,1,1,2,2

    2,0,2,1,0,0
    0,0,2,1,0,2
    0,0,0,1,2,2

    1,0,2,1,0,0
    0,0,2,1,0,1
    0,0,2,1,0,1
    0,0,1,1,0,2
    0,0,0,1,1,2



     */

}
