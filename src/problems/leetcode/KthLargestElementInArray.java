package problems.leetcode;

/**
 * https://leetcode.com/problems/kth-largest-element-in-an-array
 */
public class KthLargestElementInArray {

    public static void main(String[] args) {
        int[] nums = {1, 5, 9, 6, 2, 0, 4, 7};
        int k = 5;
        System.out.println(findKthLargest(nums, k));
    }

    public static int findKthLargest(int[] nums, int k) {
        k--;
        int l = 0, r = nums.length - 1;
        while (true) {
            int m = partition(nums, l, r);
            if (m > k) {
                r = m - 1;
            } else if (m < k) {
                l = m + 1;
            } else {
                return nums[k];
            }
        }
    }

    private static int partition(int[] nums, int l, int r) {
        int i = l;
        for (int j = l, pivot = nums[r]; j < r; j++) {
            if (nums[j] > pivot) {
                swap(nums, i++, j);
            }
        }

        swap(nums, i, r);
        return i;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
