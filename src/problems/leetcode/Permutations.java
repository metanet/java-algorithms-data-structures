package problems.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/permutations/
 */
public class Permutations {

    private List<List<Integer>> permutations = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        permute(nums, 0);
        return permutations;
    }

    private void permute(int[] nums, int i) {
        if (i == nums.length - 1) {
            List<Integer> list = new ArrayList<>(nums.length);
            for (int num : nums) {
                list.add(num);
            }
            permutations.add(list);
            return;
        }

        permute(nums, i + 1);

        for (int j = i + 1; j < nums.length; j++) {
            swap(nums, i, j);
            permute(nums, i + 1);
            swap(nums, i, j);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int num = nums[i];
        nums[i] = nums[j];
        nums[j] = num;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4};

        new Permutations().permute(nums);
    }


}
