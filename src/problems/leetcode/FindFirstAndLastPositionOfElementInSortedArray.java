package problems.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class FindFirstAndLastPositionOfElementInSortedArray {

    public static int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length < 1) {
            return new int[]{-1, -1};
        }

        // find first index of target
        int first = -1;
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (nums[m] < target) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        if (l >= nums.length || nums[l] != target) {
            return new int[]{-1, -1};
        }

        first = l;
        // find last index of target
        r = nums.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (nums[m] > target) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }

        return new int[]{first, r};
    }

    public static int[] searchRange2(int[] nums, int target) {
        int startIndex = firstInsertionIndex(nums, target);

        // assert that `startIndex` is within the array bounds and that `target`
        // is actually in `nums`.
        if (startIndex == nums.length || nums[startIndex] != target) {
            return new int[]{-1, -1};
        }

        return new int[]{startIndex, lastInsertionIndex(nums, target)};
    }

    private static int firstInsertionIndex(int[] nums, int target) {
        int l = 0, r = nums.length;

        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] >= target) {
                r = m;
            } else {
                l = m + 1;
            }
        }

        return l;
    }

    private static int lastInsertionIndex(int[] nums, int target) {
        int l = 0, r = nums.length;

        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] > target) {
                r = m;
            } else {
                l = m + 1;
            }
        }

        return l - 1;
    }

    public static void main(String[] args) {
        //        int[] nums = new int[]{1, 3, 3, 3, 4};
        int[] nums = new int[]{1, 3, 3, 3, 3, 3, 3, 7, 8, 9};
        System.out.println(Arrays.toString(searchRange(nums, 3)));
    }
}
