package problems.leetcode;

/**
 * https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
 */
public class LongestSubstringWithAtMostKDistinctCharacters {

    public static void main(String[] args) {
        String s = "cacbcaaccbccaccccccbcaba";
        //        String s = "eceba";
        int k = 2;
        int maxLen = lengthOfLongestSubstringKDistinct(s, k);
        System.out.println(maxLen);
    }

    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k == 0) {
            return 0;
        }

        int[] counts = new int[256];
        int distinctLetters = 0, maxLen = 0, start = 0, strLen = s.length();

        for (int i = 0; i < strLen; i++) {
            if (counts[s.charAt(i)]++ == 0) {
                distinctLetters++;
            }

            if (distinctLetters > k) {
                while (counts[s.charAt(start)] > 1) {
                    counts[s.charAt(start)]--;
                    start++;
                }

                counts[s.charAt(start)]--;
                start++;
                distinctLetters--;
            }

            maxLen = Math.max(maxLen, i - start + 1);
        }

        maxLen = Math.max(maxLen, strLen - start);

        return maxLen;
    }

}
