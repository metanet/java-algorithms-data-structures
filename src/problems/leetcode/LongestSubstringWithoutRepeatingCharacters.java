package problems.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public static int lengthOfLongestSubstringBruteForce(String s) {
        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {
            Set<Character> chars = new HashSet<>();
            int j = i;
            for (; j < s.length(); j++) {
                if (!chars.add(s.charAt(j))) {
                    break;
                }
            }
            maxLength = Math.max(maxLength, chars.size());
        }

        return maxLength;
    }

    public static int lengthOfLongestSubstring(String s) {
        int maxLength = 0, start = 0, n = s.length();
        Map<Character, Integer> indices = new HashMap<>();
        for (int i = 0; i < n; i++) {
            Integer prev = indices.put(s.charAt(i), i);
            if (prev == null || prev < start) {
                continue;
            }

            maxLength = Math.max(maxLength, i - start);
            start = prev + 1;
        }

        maxLength = Math.max(maxLength, n - start);

        return maxLength;
    }

    public static void main(String[] args) {
//        String s = "abcabcbb";
//        String s = "bbbbb";
//        String s = "pwwkew";
        String s = "abba";
        System.out.println(lengthOfLongestSubstring(s));
    }

}
