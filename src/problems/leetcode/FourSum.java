package problems.leetcode;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// https://leetcode.com/problems/4sum/description/
public class FourSum {

    public static void main(String[] args) {
        System.out.println(fourSum(new int[] { 1, 0, -1, 0, -2, 2 }, 0));
        System.out.println(fourSum(new int[] { 1000000000, 1000000000, 1000000000, 1000000000 }, -294967296));
    }

    // runtime: O(N^3)
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        // 0, 1, 2, 3, 4, 5
        // -2, -1, 0, 0, 1, 2
        // target = 0
        // i j
        // remainingTarget = 3
        // i j k l
        // i j k l
        // i j k l
        // i j k l

        if (nums.length < 4) {
            return Collections.emptyList();
        }

        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                long remaining = (long) target - (nums[i] + nums[j]);
                int k = j + 1, l = nums.length - 1;
                while (k < l) {
                    int sum = nums[k] + nums[l];
                    if (sum > remaining) {
                        l--;
                    } else if (sum < remaining) {
                        k++;
                    } else {
                        result.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));

                        k++;
                        while (k < l && nums[k] == nums[k - 1]) {
                            k++;
                        }

                        l--;
                        while (k < l && nums[l] == nums[l + 1]) {
                            l--;
                        }
                    }
                }
            }
        }

        return result;
    }

}
