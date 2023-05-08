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

    // runtime: O(N^2)
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> indices = new ArrayList<>();
        Arrays.sort(nums); // O(NlgN)

        // O(N^2)
        for (int i = 0, len = nums.length; i < len - 2; i++) { // O(N)
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int j = i + 1, k = len - 1;
            while (j < k) { // O(N)
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

    // runtime: O(N^3)
    public List<List<Integer>> threeSumBruteForce(int[] nums) {
        List<List<Integer>> indices = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }            
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                for (int k = j + 1; k < nums.length; k++) {
                    if (k > j + 1 && nums[k] == nums[k - 1]) {
                        continue;
                    } else if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> l = new ArrayList();
                        l.add(nums[i]);
                        l.add(nums[j]);
                        l.add(nums[k]);
                        indices.add(l);
                    }
                }
            }
        }

        return indices;
    }

}
