package problems.leetcode;

import java.util.Arrays;

public class SelectionSort {

    // space complexity: O(1)
    // runtime complexity: O(N^2)
    public static void sort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            int k = i;
            for (int j = i; j < nums.length; j++) {
                if (nums[k] > nums[j]) {
                    k = j;
                }
            }
            int num = nums[i];
            nums[i] = nums[k];
            nums[k] = num;
        }

        System.out.println(Arrays.toString(nums));
    }

    public static void main(String[] args) {
        sort(new int[] { 1, 2, 3, 4, 5 });
        sort(new int[] { 5, 4, 3, 2, 1 });
        sort(new int[] { 1, 2, 3, 4, 5, 4, 3, 2, 1 });
    }

}
