package problems.leetcode;

/**
 * https://leetcode.com/problems/regular-expression-matching/
 * https://www.youtube.com/watch?v=l3hda49XcDE
 */
public class RegularExpressionMatching {

    public static void main(String[] args) {
        String s = "abc";
        String p = "a*bc";

        System.out.println(new RegularExpressionMatching().isMatch(s, p));
        System.out.println(new RegularExpressionMatching().isMatchDP(s, p));
    }


    private Boolean[][] results;

    public boolean isMatch(String text, String pattern) {
        results = new Boolean[text.length() + 1][pattern.length() + 1];
        return isMatch(text, pattern, 0, 0);
    }

    private boolean isMatch(String text, String pattern, int i, int j) {
        if (results[i][j] != null) {
            return results[i][j];
        }

        boolean success;
        if (j == pattern.length()) {
            success = i == text.length();
        } else {
            boolean match = (i < text.length() && (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.'));

            if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
                // zero occurrence of pattern[j]*
                success = isMatch(text, pattern, i, j + 2);
                if (!success) {
                    // one or more occurrence of pattern[j]*
                    success = match && isMatch(text, pattern, i + 1, j);
                }
            } else if (match) {
                success = isMatch(text, pattern, i + 1, j + 1);
            } else {
                success = false;
            }
        }

        results[i][j] = success;
        return success;
    }

    public boolean isMatchDP(String text, String pattern) {
        /*
        dp[i][j] =
            if s[i] == p[j] || p[j] == '.'
                dp[i-1][j-1]
            if p[j] == '*'
                dp[i][j-2] (zero occurrence)
                dp[i-1][j] if s[i] == p[j-1] || p[j-1] == '.'
            Otherwise
                false
         */

        int textLen = text.length(), patternLen = pattern.length();
        boolean[][] dp = new boolean[textLen + 1][patternLen + 1];
        dp[0][0] = true;

        // Deals with patterns like a* or a*b* or a*b*c*
        for (int i = 1; i <= patternLen; i++) {
            if (pattern.charAt(i - 1) == '*') {
                dp[0][i] = dp[0][i - 2];
            }
        }

        for (int i = 1; i <= textLen; i++) {
            for (int j = 1; j <= patternLen; j++) {
                char t = text.charAt(i - 1), p = pattern.charAt(j - 1);
                if (p == '.' || p == t) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p == '*') {
                    dp[i][j] = dp[i][j - 2]; // zero occurrence
                    if (!dp[i][j] && (pattern.charAt(j - 2) == '.' || pattern.charAt(j - 2) == t)) {
                        // one or more occurrences
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }

        return dp[textLen][patternLen];
    }

}
