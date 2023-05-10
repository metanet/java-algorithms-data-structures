package problems.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/permutations-ii/
 */
public class PermutationsII {

    public static List<List<Integer>> permuteUnique(int[] nums) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            int c = counter.getOrDefault(num, 0);
            counter.put(num, c + 1);
        }

        List<List<Integer>> results = new ArrayList<>();
        List<Integer> comb = new ArrayList<>();
        permuteUnique(comb, nums.length, counter, results);
        return results;
    }

    private static void permuteUnique(
            List<Integer> comb,
            int n,
            Map<Integer, Integer> counter,
            List<List<Integer>> results) {
        if (comb.size() == n) {
            // make a deep copy of the resulting permutation,
            // since the permutation would be backtracked later.
            results.add(new ArrayList<Integer>(comb));
            return;
        }

        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            int num = entry.getKey();
            int count = entry.getValue();
            if (count == 0) {
                continue;
            }

            // add this number into the current combination
            comb.add(num);
            counter.put(num, count - 1);

            // continue the exploration
            permuteUnique(comb, n, counter, results);

            // revert the choice for the next exploration
            comb.remove(comb.size() - 1);
            counter.put(num, count);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 2, 2, 1, 1 };

        List<List<Integer>> permutations = permuteUnique(nums);
        permutations.forEach(System.out::println);
    }

}
