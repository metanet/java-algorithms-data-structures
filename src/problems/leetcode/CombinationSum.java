package problems.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// https://leetcode.com/problems/combination-sum/
public class CombinationSum {

    // runtime: O(2 ^ N) where N is number of candidates
    // space: O(N)
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length < 1) {
            return Collections.emptyList();
        }
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        combinationSum(candidates, target, 0, 0, new ArrayList<>(), result);
        return result;
    }

    private static void combinationSum(int[] candidates, int target, int i, int currentSum, List<Integer> current,
            List<List<Integer>> result) {
        if (i >= candidates.length) {
            return;
        }

        int candidate = candidates[i];
        if (currentSum + candidate == target) {
            current.add(candidate);
            result.add(new ArrayList<>(current));
            current.remove(current.size() - 1);
            return;
        } else if (currentSum + candidate < target) {
            current.add(candidate);
            combinationSum(candidates, target, i, currentSum + candidate, current, result);
            current.remove(current.size() - 1);
        } else {
            return;
        }

        combinationSum(candidates, target, i + 1, currentSum, current, result);
    }

    public static void main(String[] args) {
        System.out.println(combinationSum(new int[] { 2, 3, 6, 7 }, 6));
        System.out.println(combinationSum(new int[] { 4, 2, 8 }, 8));
    }

}
