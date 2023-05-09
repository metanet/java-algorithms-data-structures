package problems.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/next-permutation/
 */
public class NextPermutation {

    public static void main(String[] args) {
        //        int[] nums = new int[] {1, 2, 3, 4};
        //        System.out.println(Arrays.toString(nums));
        //        nextPermutation(nums);
        //        System.out.println(Arrays.toString(nums));
        //        nextPermutation(nums);
        //        System.out.println(Arrays.toString(nums));
        //        nextPermutation(nums);
        //        System.out.println(Arrays.toString(nums));
        //        nextPermutation(nums);
        //        System.out.println(Arrays.toString(nums));
        //        nextPermutation(nums);
        //        System.out.println(Arrays.toString(nums));
        //        nextPermutation(nums);
        //        System.out.println(Arrays.toString(nums));
        //        nextPermutation(nums);
        //        System.out.println(Arrays.toString(nums));
        //        nextPermutation(nums);
        //        System.out.println(Arrays.toString(nums));
        //        nextPermutation(nums);
        //        System.out.println(Arrays.toString(nums));
        //        nextPermutation(nums);
        //        System.out.println(Arrays.toString(nums));
        //        nextPermutation(nums);
        //        System.out.println(Arrays.toString(nums));
        //        nextPermutation(nums);
        //        System.out.println(Arrays.toString(nums));
        //        nextPermutation(nums);
        //        System.out.println(Arrays.toString(nums));
        //        nextPermutation(nums);
        //        System.out.println(Arrays.toString(nums));
        //        nextPermutation(nums);
        //        System.out.println(Arrays.toString(nums));
        //        nextPermutation(nums);
        //        System.out.println(Arrays.toString(nums));
        //        nextPermutation(nums);
        //        System.out.println(Arrays.toString(nums));
        //        nextPermutation(nums);
        //        System.out.println(Arrays.toString(nums));
        //        nextPermutation(nums);
        //        System.out.println(Arrays.toString(nums));
        //        nextPermutation(nums);
        //        System.out.println(Arrays.toString(nums));
        //        nextPermutation(nums);
        //        System.out.println(Arrays.toString(nums));
        //        nextPermutation(nums);
        //        System.out.println(Arrays.toString(nums));
        //        nextPermutation(nums);
        //        System.out.println(Arrays.toString(nums));
        //        nextPermutation(nums);
        //        System.out.println(Arrays.toString(nums));

        int[] nums = new int[]{4, 8, 8, 8, 9, 7, 7, 6, 4, 3};
        System.out.println(Arrays.toString(nums));
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    // runtime: O(N)
    // space: O(1)
    public static void nextPermutation(int[] nums) {
        // intuition:
        // find the right-most number where it is greater than on its left
        // swap them then reverse the right side

        // Starting from right, we need to find the first pair of two successive
        // nums[i] and nums[i−1] where nums[i] > nums[i-1]. Now, no rearrangements
        // to the right of nums[i-1] can create a larger permutation.
        int len = nums.length, i = len - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if (i >= 0) {
            // find the first index j such that nums[j] > nums[i]
            // in other words, find the index of the first number greater than
            // nums[i] and on the right of index = i
            int j = len - 1;
            while (j >= i && nums[j] <= nums[i]) {
                j--;
            }

            swap(nums, i, j);
        }

        reverse(nums, i + 1);
    }

    private static void reverse(int[] nums, int i) {
        int j = nums.length - 1;
        while (i < j) {
            swap(nums, i++, j--);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
