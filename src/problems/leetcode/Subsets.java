package problems.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/subsets/
 */
public class Subsets {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> subsets = new Subsets().subsets(nums);
        for (List<Integer> subset : subsets) {
            System.out.println(subset);
        }
    }

    List<List<Integer>> subsets = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        for (int k = nums.length; k > 0; k--) {
            for (int i = 0; i < nums.length; i++) {
                populate(nums, i, k, new ArrayList<>());
            }
        }

        subsets.add(Collections.emptyList());

        return subsets;
    }

    private void populate(int[] nums, int i, int k, List<Integer> subset) {
        subset.add(nums[i]);

        if (k == 1) {
            subsets.add(new ArrayList<>(subset));
        } else {
            for (int j = i + 1; j < nums.length; j++) {
                populate(nums, j, k - 1, subset);
            }
        }

        subset.remove(subset.size() - 1);
    }

}
