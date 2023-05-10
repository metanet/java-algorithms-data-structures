package problems.leetcode;

import java.util.Arrays;

// https://leetcode.com/problems/first-missing-positive/
public class FirstMissingPositive {

    public static int firstMissingPositive(int[] nums) {
        // https://leetcode.com/problems/first-missing-positive/solutions/17214/Java-simple-solution-with-documentation/

        // Ignore all numbers <=0 and >n since they are outside the range of possible
        // answers (which we proved was [1..n]). We do this by replacing them with the
        // value n+1.
        // For all other integers <n+1, mark their bucket (cell) to indicate the integer
        // exists. (*see below)
        // Find the first cell not marked, that is the first missing integer. If you did
        // not find an unmarked cell, there was no missing integer, so return n+1.

        int n = nums.length;

        // 1. mark numbers (num < 0) and (num > n) with a special marker number (n+1)
        // (we can ignore those because if all number are > n then we'll simply return
        // 1)
        for (int i = 0; i < n; i++) {
            if (nums[i] < 1 || nums[i] > n) {
                nums[i] = n + 1;
            }
        }
        System.out.println(Arrays.toString(nums));
        // note: all number in the array are now positive, and on the range 1..n+1

        // 2. mark each cell appearing in the array, by converting the index for that
        // number to negative
        for (int i = 0; i < n; i++) {
            int num = Math.abs(nums[i]);
            if (num > n) {
                continue;
            }
            int numIndex = num - 1;
            if (nums[numIndex] > 0) { // prevents double negative operations
                nums[numIndex] = -1 * nums[numIndex];
            }
        }
        System.out.println(Arrays.toString(nums));

        // 3. find the first cell which isn't negative (doesn't appear in the array)
        for (int i = 0; i < n; i++) {
            if (nums[i] >= 0) {
                return i + 1;
            }
        }

        // 4. no positive numbers were found, which means the array contains all numbers
        // 1..n
        return n + 1;
    }

    public static void main(String[] args) {
        System.out.println(firstMissingPositive(new int[] { 1, 2, 0 }));
        System.out.println(firstMissingPositive(new int[] { 3, 4, -1, 1 }));
        System.out.println(firstMissingPositive(new int[] { 7, 8, 9, 11, 12 }));
        System.out.println(firstMissingPositive(new int[] { 1, 8, 9, 11, 12 }));
    }

}
