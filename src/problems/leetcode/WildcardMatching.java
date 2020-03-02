package problems.leetcode;

/**
 * https://leetcode.com/problems/wildcard-matching/
 * https://www.youtube.com/watch?v=3ZDZ-N0EPV0
 * <p>
 * dynamic programming
 */
public class WildcardMatching {

    // 0 : not known yet
    // 1 : success
    // 2 : fail
    private int[][] results;

    public boolean isMatch(String text, String pattern) {
        results = new int[text.length() + 1][pattern.length() + 1];
        return isMatch(text, removeMultipleStars(pattern), 0, 0);
    }

    private boolean isMatch(String text, String pattern, int i, int j) {
        if (results[i][j] != 0) {
            return results[i][j] == 1;
        }

        boolean success;
        if (j == pattern.length()) {
            success = (i == text.length());
        } else {
            boolean match = (i < text.length() && (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '?'));
            if (match) {
                success = isMatch(text, pattern, i + 1, j + 1);
            } else if (pattern.charAt(j) == '*') {
                success = isMatch(text, pattern, i, j + 1) // zero occurrence
                        || (i + 1 <= text.length() && isMatch(text, pattern, i + 1, j)); // one or more occurrence
            } else {
                success = false;
            }
        }

        results[i][j] = success ? 1 : 2;
        return success;
    }

    public boolean isMatchDP(String s, String p) {
        /*

        T[i][j] =
            T[i-1][j-1] if text[i] == pattern[j] || pattern[j] == '?'
            T[i-1][j] || T[i][j-1] if pattern[j] == '*'
            False otherwise

         */

        //replace multiple * with one *
        //e.g a**b***c --> a*b*c
        p = removeMultipleStars(p);

        char[] str = s.toCharArray();
        char[] pattern = p.toCharArray();

        boolean[][] dp = new boolean[str.length + 1][pattern.length + 1];

        if (pattern.length > 0 && pattern[0] == '*') {
            dp[0][1] = true;
        }

        dp[0][0] = true;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (pattern[j-1] == '?' || str[i-1] == pattern[j-1]) {
                    dp[i][j] = dp[i-1][j-1];
                } else if (pattern[j-1] == '*'){
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }
            }
        }

        return dp[str.length][pattern.length];
    }

    private static String removeMultipleStars(String pattern) {
        if (pattern.length() == 0) {
            return pattern;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(pattern.charAt(0));

        for (int i = 1; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (c != '*' || pattern.charAt(i - 1) != '*') {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "ensar";
        String p = "?n*r";
        WildcardMatching solution = new WildcardMatching();
        System.out.println(solution.isMatch(s, p));
        System.out.println(solution.isMatchDP(s, p));
    }

}
