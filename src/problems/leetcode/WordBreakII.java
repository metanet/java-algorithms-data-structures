package problems.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/word-break-ii/
 */
public class WordBreakII {


    public static void main(String[] args) {
        String s = "catsanddog";
        List<String> wordDict = Arrays.asList("cat", "cats", "and", "sand", "dog");

        for (String str : wordBreak(s, wordDict)) {
            System.out.println("> " + str);
        }
    }


    public static List<String> wordBreak(String s, List<String> wordDict) {
        List<String> strings = wordBreak(new HashSet<>(wordDict), s, 0, new HashMap<>());
        return strings != null ? strings : Collections.emptyList();
    }

    private static List<String> wordBreak(Set<String> dictionary, String str, int i, Map<Integer, List<String>> memo) {
        if (i == str.length()) {
            return Collections.emptyList();
        } else if (memo.containsKey(i)) {
            return memo.get(i);
        }

        List<String> sentences = new ArrayList<>();

        for (int j = i + 1; j <= str.length(); j++) {
            String word = str.substring(i, j);
            if (dictionary.contains(word)) {
                List<String> tails = wordBreak(dictionary, str, j, memo);
                if (tails != null) {
                    if (tails.isEmpty()) {
                        sentences.add(word);
                    } else {
                        for (String subSubStr : tails) {
                            sentences.add(word + " " + subSubStr);
                        }
                    }
                }
            }
        }

        sentences = sentences.size() > 0 ? sentences : null;
        memo.put(i, sentences);

        return sentences;
    }

}
