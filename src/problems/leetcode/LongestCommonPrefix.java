package problems.leetcode;

import java.util.Optional;

/**
 * https://leetcode.com/problems/longest-common-prefix/
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        String[] strs = { "c", "c" };
        System.out.println(longestCommonPrefix(strs));
    }

    // runtime: O(N)
    // space: O(1)
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0;; i++) {
            Optional<Character> c = Optional.empty();
            for (String s : strs) {
                if (i >= s.length()) {
                    c = Optional.empty();
                    break;
                } else if (c.isEmpty()) {
                    c = Optional.of(s.charAt(i));
                } else if (c.get() != s.charAt(i)) {
                    c = Optional.empty();
                    break;
                }
            }
            if (c.isEmpty()) {
                break;
            } else {
                sb.append(c.get());
            }
        }

        return sb.toString();
    }

}
