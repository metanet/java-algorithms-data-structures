package problems.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/continuous-subarray-sum/
 */
public class ContinuousSubarraySum {

    public static void main(String[] args) {
        //        int[] nums = new int[]{0, 1, 0};
        int[] nums = new int[]{23, 2, 4, 6, 7};
        int k = 6;

        boolean success = checkSubarraySum(nums, k);
        System.out.println(success);
    }

    public static boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        for (int i = 0, sum = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) {
                sum %= k;
            }

            // if I arrive at sum twice, it means sum increased as k.
            if (map.containsKey(sum)) {
                if (i - map.get(sum) > 1) {
                    return true;
                }
            } else {
                map.put(sum, i);
            }
        }

        return false;
    }
}
