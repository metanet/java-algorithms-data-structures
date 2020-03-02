package problems.leetcode;

/**
 * https://leetcode.com/problems/increasing-triplet-subsequence/
 */
public class IncreasingTripletSubsequence {

    public static void main(String[] args) {
        int[] nums = {9, 5, 2, 6, 1, 8};
        System.out.println(increasingTriplet(nums));
    }

    public static boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }

        Integer num1 = null, num2 = null, num3 = null;
        for (int num : nums) {
            if (num1 == null || num <= num1) {
                num1 = num;
            } else if (num2 == null || num <= num2) {
                num2 = num;
            } else if (num3 == null || num <= num3) {
                num3 = num;
            }
        }

        return num2 != null && num3 != null;
    }

    /*

    // 9 5 2 6 1 8

     */

}
