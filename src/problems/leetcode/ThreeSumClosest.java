package problems.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/3sum-closest/
 */
public class ThreeSumClosest {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 0};
        System.out.println(threeSumClosest(nums, -100));
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length, smallestAbsDiff = Integer.MAX_VALUE, closestSum = Integer.MAX_VALUE;
        for (int i = 0; i < n - 2; i++) {
            int j = i + 1, k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k], diff = sum - target, absDiff = Math.abs(diff);
                if (diff == 0) {
                    return sum;
                } else if (diff < 0) {
                    j++;
                } else {
                    k--;
                }

                if (smallestAbsDiff > absDiff) {
                    smallestAbsDiff = absDiff;
                    closestSum = sum;
                }
            }
        }

        return closestSum;
    }

}
