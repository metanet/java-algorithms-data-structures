package problems.leetcode;

import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/word-ladder/
 */
public class WordLadder {

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
//        String beginWord = "a";
//        String endWord = "c";
//        String[] words = {"a", "b", "c"};

        System.out.println(ladderLength(beginWord, endWord, Arrays.asList(words)));
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String, Set<String>> adjacentWordsMap = new HashMap<>();
        for (int i = 0; i < wordList.size(); i++) {
            String word1 = wordList.get(i);
            for (int j = i + 1; j < wordList.size(); j++) {
                String word2 = wordList.get(j);
                if (isAdjacent(word1, word2)) {
                    adjacentWordsMap.computeIfAbsent(word1, w -> new HashSet<>()).add(word2);
                    adjacentWordsMap.computeIfAbsent(word2, w -> new HashSet<>()).add(word1);
                }
            }
        }

        for (String word : wordList) {
            if (!beginWord.equals(word) && isAdjacent(beginWord, word)) {
                adjacentWordsMap.computeIfAbsent(beginWord, w -> new HashSet<>()).add(word);
                adjacentWordsMap.computeIfAbsent(word, w -> new HashSet<>()).add(beginWord);
            }
        }

        // word -> distance
        Queue<Map.Entry<String, Integer>> queue = new ArrayDeque<>();
        queue.add(new AbstractMap.SimpleEntry<>(beginWord, 1));

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        while (!queue.isEmpty()) {
            Map.Entry<String, Integer> e = queue.remove();
            String word = e.getKey();
            int distance = e.getValue();

            for (String adjacentWord : adjacentWordsMap.getOrDefault(word, Collections.emptySet())) {
                if (adjacentWord.equals(endWord)) {
                    return distance + 1;
                } else if (visited.add(adjacentWord)) {
                    queue.add(new AbstractMap.SimpleEntry<>(adjacentWord, distance + 1));
                }
            }
        }

        return 0;
    }

    private static boolean isAdjacent(String s1, String s2) {
        boolean mismatch = false;
        for (int i = 0, len = s1.length(); i < len; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (mismatch) {
                    return false;
                }

                mismatch = true;
            }
        }

        return true;
    }

}
