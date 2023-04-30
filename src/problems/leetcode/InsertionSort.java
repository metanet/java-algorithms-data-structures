package problems.leetcode;

import java.util.Arrays;

public class InsertionSort {

    // runtime: O(N^2)
    // space: O(1)
    public static void sort(int[] nums) {
        if (nums == null || nums.length < 1) {
            return;
        }

        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j > 0 && nums[j] < nums[j - 1]; j--) {
                int num = nums[j];
                nums[j] = nums[j - 1];
                nums[j - 1] = num;
            }
        }

        System.out.println(Arrays.toString(nums));
    }

    public static void main(String[] args) {
        sort(new int[] { 1, 2, 3, 4, 5 });
        sort(new int[] { 5, 4, 3, 2, 1 });
        sort(new int[] { 1, 2, 3, 4, 5, 4, 3, 2, 1 });
    }

}
