package problems.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/subarray-sum-equals-k/
 */
public class SubarraySumEqualK {

    public static void main(String[] args) {
                int[] nums = {1, 5, 2, 4, 1, 1, 2, 2, 4};
                int k = 6;
//        int[] nums = {28, 54, 7, -70, 22, 65, -6};
//        int k = 100;
//        int[] nums = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
//        int k = 0;
        System.out.println(subarraySumON(nums, k));
    }

    public static int subarraySum(int[] nums, int k) {
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }

        return count;
    }

    public static int subarraySumON(int[] nums, int k) {
        int count = 0, sum = 0;

        // key   : sum
        // value : how many indices have this sum
        Map<Integer, Integer> cumulativeSums = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum == k) {
                count++;
            }

            count += cumulativeSums.getOrDefault(sum - k, 0);
            cumulativeSums.merge(sum, 1, Integer::sum);
        }

        return count;
    }

    /**
     *
     * k = 6
     * 1 5 2 4 1 1 2 2 4
     *
     *
     * 1 5
     * 2 4
     * 4 1 1
     * 1 1 2 2
     * 2 4
     *
     * i = 1
     * cur sum = 1
     * i = 2
     * cur sum = 6
     *
     *               0  1  2  3  4  5  6  7  8
     * int[] nums = {1, 5, 2, 4, 1, 1, 2, 2, 4};
     * int k = 6;
     *
     * 1  -> 0
     * 6  -> 1
     * 8  -> 2
     * 12 -> 3  12 - 6 (index=1)
     * 13 -> 4
     * 14 -> 5  14 - 8 (index=2)
     * 16 -> 6
     * 18 -> 7 18 - 12 (index=3)
     * 22 -> 8 22 - 16 (index=6)
     *
     *
     */
}
