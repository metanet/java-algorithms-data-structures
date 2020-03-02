package problems.leetcode;

/**
 * https://leetcode.com/problems/missing-number/
 */
public class MissingNumber {

    public static void main(String[] args) {
        int[] nums = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        System.out.println(missingNumber(nums));
    }

    public static int missingNumber(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        int n = nums.length;
        return n * (n + 1) / 2 - sum;
    }

}
