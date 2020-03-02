package problems.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/single-number-iii/
 */
public class SingleNumberIII {

    public static int[] singleNumber(int[] nums) {
        // say that the 2 unique numbers are: x, y
        // i keeps the bits that differ between x and y
        int i = 0;
        for (int num : nums) {
            i ^= num;
        }

        // right-most 1-bit diff between x and y
        int diff = i & (-i);
        int x = 0;
        // bitmask which will contain only x
        for (int num : nums) {
            if ((num & diff) != 0) {
                // duplicate numbers will get eliminated because of ^
                // since diff keeps the right-most 1-bit,
                // only one of x and y (i.e., x) will satisfy this condition
                x ^= num;
            }
        }

        return new int[]{x, x ^ i};
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 3};
        System.out.println(Arrays.toString(singleNumber(nums)));
    }

}
