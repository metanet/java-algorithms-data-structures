package problems.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/top-k-frequent-elements/
 */
public class TopKFrequentElements {

    public List<Integer> topKFrequent(int[] nums, int k) {
        if (nums == null || k < 1) {
            return Collections.emptyList();
        }

        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.merge(num, 1, Integer::sum);
        }

        Comparator<Map.Entry<Integer, Integer>> c = Comparator.comparingInt(Map.Entry::getValue);
        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>(c);

        for (Map.Entry<Integer, Integer> e : counts.entrySet()) {
            heap.add(e);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        List<Integer> topK = new ArrayList<>(k);
        while (heap.size() > 0) {
            topK.add(heap.poll().getKey());
        }

        Collections.reverse(topK);
        return topK;
    }

}
