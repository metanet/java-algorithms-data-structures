package problems.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/rotate-array/
 */
public class RotateArray {

    public static void rotate(int[] nums, int k) {
        if (nums == null) {
            return;
        }

        int n = nums.length;
        if (n < 2 || k == 0 || n == k) {
            return;
        }

        int[] rotated = new int[n];
        for (int i = 0; i < n; i++) {
            rotated[(i + k) % n] = nums[i];
        }

        System.arraycopy(rotated, 0, nums, 0, n);
    }

    public void rotate2(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6};
        int k = 2;

        rotate(nums, nums.length + k);

        System.out.println(Arrays.toString(nums));
    }

}
