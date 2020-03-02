package problems.leetcode;

/**
 * https://leetcode.com/problems/find-peak-element/
 */
public class FindPeakElement {

    public static int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > nums[mid + 1]) {
                // peak is on the left
                r = mid;
            } else {
                // peak is on the right
                l = mid + 1;
            }
        }

        return l;
    }

    public static void main(String[] args) {
                int[] nums = new int[]{1, 2, 3, 4, 0};
//                int[] nums = new int[]{1, 1, 1, 1};
//        int[] nums = new int[]{1, 1, 1, 3, 5, 6, 4};
        //        int[] nums = new int[]{1, 2};
        System.out.println(findPeakElement(nums));
    }

}
