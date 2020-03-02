package problems.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/product-of-array-except-self/
 */
public class ProductOfArrayExceptSelf {

    public static void main(String[] args) {
        int[] nums = {3, 5, 2, 7};
        int[] output = productExceptSelf(nums);

        System.out.println(Arrays.toString(output));
    }

    public static int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        } else if (nums.length == 1) {
            return new int[]{1};
        }

        int product = 1, len = nums.length;
        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            result[i] = product;
            product *= nums[i];
        }

        product = 1;
        for (int i = len - 1; i >= 0; i--) {
            result[i] *= product;
            product *= nums[i];
        }

        return result;
    }

    /*
     * solve it in O(n) without division
     *
     * Input:  [1,2,3,4]
     * Output: [24,12,8,6]
     *
     *
     *
     *
     * input:  3 , 5,  2,   7
     * output: 70, 42, 135, 30
     *
     * result : 210  42  105  30
     * product: 210
     *
     */

}
