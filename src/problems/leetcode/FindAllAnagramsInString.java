package problems.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/
 */
public class FindAllAnagramsInString {

    public static void main(String[] args) {
        String s = "abaacbabc";
        String p = "abc";

        List<Integer> indices = findAnagrams(s, p);
        System.out.println(indices);
    }

    public static List<Integer> findAnagrams(String s, String p) {
        if (s == null || p == null || s.length() < p.length()) {
            return Collections.emptyList();
        }

        int start = 0, end = 0, remaining = p.length();
        List<Integer> indices = new ArrayList<>();
        int[] requiredCounts = new int['z' - 'a' + 1];
        for (char c : p.toCharArray()) {
            requiredCounts[c - 'a']++;
        }

        while (end < s.length()) {
            if (requiredCounts[s.charAt(end) - 'a']-- > 0) {
                remaining--;
            }

            end++;

            while (remaining == 0) {
                if (++requiredCounts[s.charAt(start) - 'a'] > 0) {
                    remaining++;
                }

                if (end - start == p.length()) {
                    indices.add(start);
                }

                start++;
            }
        }

        return indices;
    }

    public List<Integer> findAnagrams2(String s, String p) {
        if (s == null || p == null || s.length() < p.length()) {
            return Collections.emptyList();
        }

        List<Integer> indices = new ArrayList<>();
        int[] expectedCounts = new int['z' - 'a' + 1], counts = new int['z' - 'a' + 1];
        int pLen = p.length();

        for (char c : p.toCharArray()) {
            expectedCounts[c - 'a']++;
        }

        for (int i = 0; i < pLen; i++) {
            counts[s.charAt(i) - 'a']++;
        }

        if (Arrays.equals(expectedCounts, counts)) {
            indices.add(0);
        }

        for (int i = pLen; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
            counts[s.charAt(i - pLen) - 'a']--;
            if (Arrays.equals(expectedCounts, counts)) {
                indices.add(i - pLen + 1); //O(26)
            }
        }

        return indices;
    }

}
