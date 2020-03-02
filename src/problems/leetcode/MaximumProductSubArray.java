package problems.leetcode;

/**
 * https://leetcode.com/problems/maximum-product-subarray/
 * <p>
 * dynamic programming
 */
public class MaximumProductSubArray {

    /*

    -2, 3, -4

     */

    public int maxProductBruteForce(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        }

        int maxP = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int m = 1;
            for (int j = i; j < nums.length; j++) {
                m *= nums[j];
                if (m > maxP) {
                    maxP = m;
                }
            }
            if (m > maxP) {
                maxP = m;
            }
        }

        return maxP;
    }

    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int maxSoFar = nums[0], minSoFar = nums[0], maxProduct = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int maxHere = Math.max(nums[i], Math.max(nums[i] * maxSoFar, nums[i] * minSoFar));
            minSoFar = Math.min(nums[i], Math.min(nums[i] * maxSoFar, nums[i] * minSoFar));
            maxSoFar = maxHere;

            maxProduct = Math.max(maxProduct, maxSoFar);
        }

        return maxProduct;
    }

    public static void main(String[] args) {
//        int[] nums = {2, 3, -2, 4};
//        int[] nums = {0, 2};
//        int[] nums = {-2, 3, -4};
        int[] nums = {2, -5, -2, -4, 3};
        MaximumProductSubArray solution = new MaximumProductSubArray();
        System.out.println(solution.maxProduct(nums));
    }

}
