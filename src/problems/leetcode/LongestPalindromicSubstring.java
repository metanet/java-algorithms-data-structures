package problems.leetcode;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 */
public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        // String s = "babadada";
        //        String s = "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        // String longestPal = new LongestPalindromicSubstring().longestPalindrome(s);
        // System.out.println(longestPal);

        System.out.println(longestPalindrome("ccc"));
    }

    // runtime: O(N^2)
    // space: O(N^2)
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        int[][] m = new int[s.length()][s.length()];
        // base
        // P(i, i) = true
        // P(i, i+1) = (Si == Si+1)
        String result = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            m[i][i] = 1;
            if (i + 1 < s.length()) {
                if (s.charAt(i) == s.charAt(i + 1)) {
                    m[i][i + 1] = 2;
                    result = s.substring(i, i + 2);
                } else {
                    m[i][i + 1] = 0;
                }
            }
        }

        // common
        // P(i, j) = (P(i + 1, j - 1) + Si == Sj)
        for (int len = 3; len <= s.length(); len++) {
            for (int i = 0; i <= s.length() - len; i++) {
                int j = i + len - 1;
                int p = m[i + 1][j - 1];
                m[i][j] = (p != 0 && s.charAt(i) == s.charAt(j)) ? p + 2 : 0;
                // System.out.println("i=" + i + ", len=" + len + ", p=" + m[i][j]);
                if (m[i][j] > result.length()) {
                    result = s.substring(i, i + len);
                }
            }
        }

        return result;
    }

    public String longestPalindromeDP(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        // P(i, j) = P(i + 1, j - 1) and S[i] == S[j]
        // P(i, i) = true
        // P(i, i + 1) = S[i] == S[i+1]

        int n = s.length(), start = 0, maxLen = 1;
        boolean[][] dp = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i;
                maxLen = 2;
            } else {
                dp[i][i + 1] = false;
            }
        }

        for (int len = 3; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;
                if (dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                    start = i;
                    maxLen = len;
                } else {
                    dp[i][j] = false;
                }
            }
        }

        return s.substring(start, start + maxLen);
    }

    // runtime: O(N^3) if the longest palindrome has length of 1
    // space: O(1)
    public String longestPalindromeBruteForce(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        String result = null;
        for (int i = 0; i < s.length(); i++) {
            for (int j = s.length() - 1; j >= i; j--) {
                String sub = s.substring(i, j + 1);
                if (result != null && sub.length() <= result.length()) {
                    continue;
                } else if (isPalindrome(sub)) {
                    result = sub;
                }
            }
        }

        return result;
    }

    private boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }

        return true;
    }


}
