package problems.leetcode;

import java.util.Arrays;
import java.util.Collections;

public class QuickSort {

    // 5, 1, 4, 8, 0, 9
    // pivot = 5
    // 1, 4, 0, 5, 8, 9

    private static void exch(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }

        int n = nums[i];
        nums[i] = nums[j];
        nums[j] = n;
    }

    private static int partition(int[] nums, int lo, int hi) {
        // Partition into nums[lo..i-1], a[i], nums[i+1..hi].
        // where a[i] will be at its final place.
        int i = lo, j = hi + 1; // left and right scan indices
        int pivot = nums[i];
        while (true) {
            // Scan right, scan left, check for scan complete, and exchange.
            while (nums[++i] < pivot) {
                if (i == hi) {
                    break;
                }
            }

            while (pivot < nums[--j]) {
                if (j == lo) {
                    break;
                }
            }

            if (i >= j) {
                break;
            }

            exch(nums, i, j);
        }

        exch(nums, lo, j); // put pivot = nums[j] into its final position
        return j; // with nums[lo..j-1] <= nums[j] <= nums[j+1..hi].
    }

    private static void sort(int[] nums, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        int p = partition(nums, lo, hi);
        sort(nums, lo, p - 1);
        sort(nums, p + 1, hi);
    }

    // space: O(1)
    // runtime:
    // - O(NlgN) on avg,
    // - O(N^2) worst case, if the input is reverse sorted for e.g.
    // we can avoid worst-case by shuffling before sort.
    public static void sort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        // shuffle the array to avoid the worst-case perf
        Collections.shuffle(Arrays.asList(nums));
        sort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    public static void main(String[] args) {
        sort(new int[] { 1, 2, 3, 4, 5 });
        sort(new int[] { 5, 4, 3, 2, 1 });
        sort(new int[] { 1, 2, 3, 4, 5, 4, 3, 2, 1 });
    }

}
