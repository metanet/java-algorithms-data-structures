package problems.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/move-zeroes/
 */
public class MoveZeroes {

    /**
     * Input: [0,1,0,3,12]
     * Output: [1,3,12,0,0]
     * <p>
     * 0, 1, 0, 3, 12
     * 1, 0, 0, 3, 12
     * 1, 3, 0, 0, 12
     * 1, 2, 12, 0, 0
     */

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void moveZeroes(int[] nums) {
        int i = -1, j = nextZeroIndex(nums, -1);
        while (true) {
            i = nextNonZeroIndex(nums, i);
            if (i == nums.length) {
                return;
            }

            if (j < i) {
                swap(nums, j, i);
                j = nextZeroIndex(nums, j);
            }
        }
    }

    private static int nextNonZeroIndex(int[] nums, int fromExc) {
        for (int i = fromExc + 1; i < nums.length; i++) {
            if (nums[i] != 0) {
                return i;
            }
        }

        return nums.length;
    }

    private static int nextZeroIndex(int[] nums, int fromExc) {
        for (int i = fromExc + 1; i < nums.length; i++) {
            if (nums[i] == 0) {
                return i;
            }
        }

        return nums.length;
    }

    private static void swap(int[] nums, int i, int j) {
        int n = nums[i];
        nums[i] = nums[j];
        nums[j] = n;
    }


}
