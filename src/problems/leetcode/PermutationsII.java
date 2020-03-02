package problems.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/permutations-ii/
 */
public class PermutationsII {

    private Set<List<Integer>> permutations = new HashSet<>();

    public List<List<Integer>> permute(int[] nums) {
        permute(nums, 0);
        return new ArrayList<>(permutations);
    }

    private void permute(int[] nums, int i) {
        if (i == nums.length - 1) {
            List<Integer> list = new ArrayList<>(nums.length);
            for (int num : nums) {
                list.add(num);
            }
//            System.out.println(list);
//            if (permutations.contains(list)) {
//                System.out.println("Already found: " + list);
//            }
            permutations.add(list);
            return;
        }

        permute(nums, i + 1);

        for (int j = i + 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                swap(nums, i, j);
                permute(nums, i + 1);
                swap(nums, i, j);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int num = nums[i];
        nums[i] = nums[j];
        nums[j] = num;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 2, 1, 1};

        List<List<Integer>> permutations = new PermutationsII().permute(nums);
        permutations.forEach(System.out::println);
    }

}
