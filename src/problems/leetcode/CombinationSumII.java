package problems.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/combination-sum-ii/
public class CombinationSumII {

    public static void main(String[] args) {
        for (List<Integer> l : combinationSum2(new int[] { 10, 1, 2, 2, 2, 2, 7, 6, 1, 1, 5 }, 8)) {
            System.out.println(l);
        }

        // for (List<Integer> l : combinationSum2(new int[] { 10, 1, 2, 2, 7, 6, 1, 1, 5
        // }, 8)) {
        // System.out.println(l);
        // }
    }

    // 1, 1, 1, 2, 2, 5, 6, 7, 10 target = 8

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // container to hold the final combinations
        List<List<Integer>> results = new ArrayList<>();
        Deque<Integer> comb = new ArrayDeque<>();
        Map<Integer, Integer> counter = new HashMap<>();
        for (int candidate : candidates) {
            counter.compute(candidate, (k, v) -> v != null ? v + 1 : 1);
        }

        // convert the counter table to a list of (num, count) tuples
        List<int[]> counterList = new ArrayList<>();
        counter.forEach((key, value) -> {
            counterList.add(new int[] { key, value });
        });

        backtrack(comb, target, 0, counterList, results);
        return results;
    }

    private static void backtrack(Deque<Integer> comb,
            int target, int i,
            List<int[]> counter,
            List<List<Integer>> results) {
        if (target == 0) {
            // make a deep copy of the current combination.
            results.add(new ArrayList<Integer>(comb));
            return;
        } else if (target < 0) {
            return;
        }

        for (int j = i; j < counter.size(); j++) {
            int[] entry = counter.get(j);
            int candidate = entry[0], freq = entry[1];
            if (freq <= 0) {
                continue;
            }

            // add a new element to the current combination
            comb.addLast(candidate);
            entry[1]--;

            // continue the exploration with the updated combination
            backtrack(comb, target - candidate, j, counter, results);

            // backtrack the changes, so that we can try another candidate
            entry[1]++;
            comb.removeLast();
        }
    }
}
