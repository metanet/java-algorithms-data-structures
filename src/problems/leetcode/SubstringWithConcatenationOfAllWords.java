package problems.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/substring-with-concatenation-of-all-words/
public class SubstringWithConcatenationOfAllWords {

    public static void main(String[] args) {
        // words array can contain duplicate words
        System.out.println(findSubstring("barfoothefoobarman", new String[] { "foo", "bar" }));
    }

    // Given N as the length of s, a as the length of words, and b as the length of each word:
    // - runtime: O((N-a*b)*a*b) 
    // - space O(a)
    // runtime: O(N * M)
    public static List<Integer> findSubstring(String s, String[] words) {
        // s = "barfoothefoobarman", words = ["foo","bar"]
        // take substrings of length 6
        // "barfoo"
        // take substring of 3
        // "bar"
        // is "bar" in words? yes
        // take next substring
        // "foo"
        // is "foo" in words? yes
        // 0 is in the result

        if (s == null || s.length() < 1 || words == null || words.length < 1) {
            return Collections.emptyList();
        }

        List<Integer> result = new ArrayList<>();

        // O(N - a * b)
        for (int i = 0, wordLen = words[0].length(),
                last = s.length() - wordLen * words.length; i <= last; i++) {
            // O(a)
            Map<String, Integer> counts = new HashMap<>();
            for (String word : words) {
                counts.compute(word, (w, count) -> count != null ? count + 1 : 1);
            }

            // O(a * b)
            for (int j = 0; j < words.length; j++) {
                // O(1)
                String candidate = s.substring(i + j * wordLen, i + j * wordLen + wordLen);
                // O(b)
                int count = counts.getOrDefault(candidate, 0);
                if (count == 0) {
                    break;
                } else if (count == 1) {
                    counts.remove(candidate);
                } else {
                    counts.put(candidate, count - 1);
                }
            }
            if (counts.isEmpty()) {
                result.add(i);
            }
        }

        return result;
    }

}
