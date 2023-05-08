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

    // runtime: O(N^2)
    public static int threeSumClosest(int[] nums, int target) {
        // nums: -1, 2, 1, -4
        // nums: -4, -1, 1, 2
        // target: 1
        // closest: MAX
        //        i   j     k
        // sum = -3
        // closest = -3
        //        i      j  k
        // sum = -1
        // closest = -1
        //            i  j  k
        // sum = 2
        // return 2

        Arrays.sort(nums);
        int closest = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (Math.abs(target - sum) < Math.abs(target - closest)) {
                    closest = sum;
                }

                if (sum < target) {
                    j++;
                } else if (sum > target) {
                    k--;
                } else {
                    return closest;
                }
            }
        }
        
        return closest;
    }

}
