package problems.leetcode;

/**
 * https://leetcode.com/problems/minimum-window-substring/
 */
public class MinimumWindowSubstring {

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";

        System.out.println(minWindow(s, t));
    }

    public static String minWindow(String s, String t) {
        if (t.length() > s.length()) {
            return "";
        }

        int remaining = t.length(), left = 0, right = 0, minStart = 0, minLen = Integer.MAX_VALUE;

        int[] requiredCounts = new int[256];
        for (int i = 0; i < remaining; i++) {
            requiredCounts[t.charAt(i)]++;
        }

        while (right < s.length()) {
            char ch = s.charAt(right);
            if (requiredCounts[ch]-- > 0) {
                remaining--;
            }

            right++;

            while (remaining == 0) {
                if (right - left < minLen) {
                    minLen = right - left;
                    minStart = left;
                }

                if (++requiredCounts[s.charAt(left)] > 0) {
                    remaining++;
                }

                left++;
            }
        }

        return minLen != Integer.MAX_VALUE ? s.substring(minStart, minStart + minLen) : "";
    }

}
