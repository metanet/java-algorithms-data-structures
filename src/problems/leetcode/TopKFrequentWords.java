package problems.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/top-k-frequent-words/
 */
public class TopKFrequentWords {

    public static void main(String[] args) {
        String[] words = {"glarko", "zlfiwwb", "nsfspyox", "pwqvwmlgri", "qggx", "qrkgmliewc", "zskaqzwo", "zskaqzwo", "ijy", "htpvnmozay", "jqrlad", "ccjel", "qrkgmliewc", "qkjzgws", "fqizrrnmif", "jqrlad", "nbuorw", "qrkgmliewc", "htpvnmozay", "nftk", "glarko", "hdemkfr", "axyak", "hdemkfr", "nsfspyox", "nsfspyox", "qrkgmliewc", "nftk", "nftk", "ccjel", "qrkgmliewc", "ocgjsu", "ijy", "glarko", "nbuorw", "nsfspyox", "qkjzgws", "qkjzgws", "fqizrrnmif", "pwqvwmlgri", "nftk", "qrkgmliewc", "jqrlad", "nftk", "zskaqzwo", "glarko", "nsfspyox", "zlfiwwb", "hwlvqgkdbo", "htpvnmozay", "nsfspyox", "zskaqzwo", "htpvnmozay", "zskaqzwo", "nbuorw", "qkjzgws", "zlfiwwb", "pwqvwmlgri", "zskaqzwo", "qengse", "glarko", "qkjzgws", "pwqvwmlgri", "fqizrrnmif", "nbuorw", "nftk", "ijy", "hdemkfr", "nftk", "qkjzgws", "jqrlad", "nftk", "ccjel", "qggx", "ijy", "qengse", "nftk", "htpvnmozay", "qengse", "eonrg", "qengse", "fqizrrnmif", "hwlvqgkdbo", "qengse", "qengse", "qggx", "qkjzgws", "qggx", "pwqvwmlgri", "htpvnmozay", "qrkgmliewc", "qengse", "fqizrrnmif", "qkjzgws", "qengse", "nftk", "htpvnmozay", "qggx", "zlfiwwb", "bwp", "ocgjsu", "qrkgmliewc", "ccjel", "hdemkfr", "nsfspyox", "hdemkfr", "qggx", "zlfiwwb", "nsfspyox", "ijy", "qkjzgws", "fqizrrnmif", "qkjzgws", "qrkgmliewc", "glarko", "hdemkfr", "pwqvwmlgri"};
        int k = 14;
        System.out.println(new TopKFrequentWords().topKFrequent(words, k));
        System.out.println(new TopKFrequentWords().topKFrequent2(words, k));
    }

    private List<Map.Entry<String, Integer>> list;
    private int K;
    private Map<String, Integer> wordCounts = new HashMap<>();

    public List<String> topKFrequent(String[] words, int k) {
        K = k - 1;
        for (String word : words) {
            wordCounts.put(word, 1 + wordCounts.getOrDefault(word, 0));
        }

        list = new ArrayList<>(wordCounts.entrySet());
        findTopK();
        List<Map.Entry<String, Integer>> topK = list.subList(0, k);
        topK.sort(this::compare);
        return topK.stream().map(Map.Entry::getKey).collect(Collectors.toList());
    }

    private int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
        int c = Integer.compare(e1.getValue(), e2.getValue());
        return c != 0 ? -c : e1.getKey().compareTo(e2.getKey());
    }

    public void findTopK() {
        int l = 0, r = list.size() - 1;
        while (true) {
            int m = partition(l, r);
            if (m > K) {
                r = m - 1;
            } else if (m < K) {
                l = m + 1;
            } else {
                return;
            }
        }
    }

    private int partition(int l, int r) {
        Map.Entry<String, Integer> pivot = list.get(r);
        int i = l;
        for (int j = l; j < r; j++) {
            if (compare(list.get(j), pivot) < 1) {
                swap(i++, j);
            }
        }

        swap(i, r);

        return i;
    }

    private void swap(int i, int j) {
        Map.Entry<String, Integer> tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }

    /**
     * Try to solve it in O(n * logk) time and O(n) extra space.
     */
    public static List<String> topKFrequent2(String[] words, int k) {
        Map<String, Integer> wordCounts = new HashMap<>();
        for (String word : words) {
            wordCounts.merge(word, 1, Integer::sum);
        }

        Comparator<Map.Entry<String, Integer>> c = Comparator
                .comparingInt((ToIntFunction<Map.Entry<String, Integer>>) Map.Entry::getValue)
                .thenComparing((e1, e2) -> -e1.getKey().compareTo(e2.getKey()));
        PriorityQueue<Map.Entry<String, Integer>> heap = new PriorityQueue<>(c);

        for (Map.Entry<String, Integer> e : wordCounts.entrySet()) {
            heap.add(e);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        List<String> result = new ArrayList<>(k);
        while (heap.size() > 0) {
            result.add(heap.poll().getKey());
        }

        Collections.reverse(result);

        return result;
    }

}
