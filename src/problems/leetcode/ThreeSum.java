package problems.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/3sum/
 */
public class ThreeSum {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(nums));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> indices = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0, len = nums.length; i < len - 2; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int j = i + 1, k = len - 1;
            while (j < k) {
                if (nums[i] + nums[j] + nums[k] > 0) {
                    k--;
                } else if (nums[i] + nums[j] + nums[k] < 0) {
                    j++;
                } else {
                    indices.add(Arrays.asList(nums[i], nums[j], nums[k]));

                    j++;
                    while (j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }

                    k--;
                    while (k > j && nums[k] == nums[k + 1]) {
                        k--;
                    }
                }
            }
        }

        return indices;
    }

}
