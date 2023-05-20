package problems.leetcode;

// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
public class FindMinimumInRotatedSortedArray {

    public static void main(String[] args) {
        // System.out.println(findMin(new int[] { 2, 1 }));
        // System.out.println(findMin(new int[] { 4, 5, 6, 7, 0, 1, 2 }));
        // System.out.println(findMin(new int[] { 6, 0, 1, 2, 3, 4, 5 }));
        System.out.println(findMin(new int[] { 0, 1, 2, 4, 5, 6, 7 }));
    }

    public static int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int m = (l + r) / 2;
            // System.out.println("l=" + l + ", r=" + r + ", m=" + m);
            if (nums[l] < nums[r] || nums[m] < nums[r]) {
                r = m;
                // System.out.println("r=" + r);
            } else {
                l = m + 1;
                // System.out.println("l=" + l);
            }
        }

        return nums[l];
    }

}
