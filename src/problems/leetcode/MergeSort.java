package problems.leetcode;

import java.util.Arrays;

public class MergeSort {

    private static void merge(int[] nums, int lo, int mid, int hi, int[] aux) {
        // Merge nums[lo..mid] with nums[mid+1..hi].
        int i = lo, j = mid + 1;
        // Copy nums[lo..hi] to nums[lo..hi].
        for (int k = lo; k <= hi; k++) {
            aux[k] = nums[k];
        }

        // Merge back to a[lo..hi].
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                nums[k] = aux[j++];
            } else if (j > hi) {
                nums[k] = aux[i++];
            } else if (aux[j] < aux[i]) {
                nums[k] = aux[j++];
            } else {
                nums[k] = aux[i++];
            }
        }
    }

    private static void sort(int[] nums, int lo, int hi, int[] aux) {
        // sort nums[lo..hi]

        if (hi <= lo) {
            return;
        }

        int mid = (lo + hi) / 2;
        sort(nums, lo, mid, aux);
        sort(nums, mid + 1, hi, aux);
        merge(nums, lo, mid, hi, aux);
    }

    // runtime: O(NlgN)
    // space: O(N)
    public static void sort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        sort(nums, 0, nums.length - 1, new int[nums.length]);
        System.out.println(Arrays.toString(nums));
    }

    public static void main(String[] args) {
        sort(new int[] { 1, 2, 3, 4, 5 });
        sort(new int[] { 5, 4, 3, 2, 1 });
        sort(new int[] { 1, 2, 3, 4, 5, 4, 3, 2, 1 });
    }

}
