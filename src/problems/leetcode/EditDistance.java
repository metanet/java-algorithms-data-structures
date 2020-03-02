package problems.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/edit-distance/
 */
public class EditDistance {

    public static void main(String[] args) {
        String s1 = "intention";
        String s2 = "execution";

        System.out.println(minDistance(s1, s2));
    }

    public static int minDistance(String word1, String word2) {
        if (word1 == null || word1.length() == 0) {
            return word2 == null ? 0 : word2.length();
        } else if (word2 == null || word2.length() == 0) {
            return word1.length();
        }

        int[][] memo = new int[word1.length()][word2.length()];
        for (int i = 0; i < word1.length(); i++) {
            Arrays.fill(memo[i], -1);
        }

        return editDistance(word1, word2, 0, 0, memo);
    }

    private static int editDistance(String s1, String s2, int i, int j, int[][] memo) {
        if (i == s1.length()) {
            return s2.length() - j;
        } else if (j == s2.length()) {
            return s1.length() - i;
        } else if (memo[i][j] != -1) {
            return memo[i][j];
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            return editDistance(s1, s2, i + 1, j + 1, memo);
        }

        int d1 = editDistance(s1, s2, i + 1, j, memo);
        int d2 = editDistance(s1, s2, i + 1, j + 1, memo);
        int d3 = editDistance(s1, s2, i, j + 1, memo);
        int d = 1 + Math.min(d1, Math.min(d2, d3));
        memo[i][j] = d;
        return d;
    }

    public static int minDistanceDP(String s1, String s2) {
        if (s1 == null || s1.length() == 0) {
            return s2 == null ? 0 : s2.length();
        } else if (s2 == null || s2.length() == 0) {
            return s1.length();
        }

        int s1Len = s1.length(), s2Len = s2.length();
        int[][] dp = new int[s1Len][s2Len];
        dp[s1Len - 1][s2Len - 1] = s1.charAt(s1Len - 1) == s2.charAt(s2Len - 1) ? 0 : 1;

        for (int i = s1Len - 2; i >= 0; i--) {
            String s1Tail = s1.substring(i);
            String s2Tail = s2.substring(s2Len - 1);
            dp[i][s2Len - 1] = s1Tail.length() - (s1Tail.contains(s2Tail) ? 1 : 0);
        }

        for (int j = s2Len - 2; j >= 0; j--) {
            String s1Tail = s1.substring(s1Len - 1);
            String s2Tail = s2.substring(j);
            dp[s1Len - 1][j] = s2Tail.length() - (s2Tail.contains(s1Tail) ? 1 : 0);
        }

        for (int i = s1Len - 2; i >= 0; i--) {
            for (int j = s2Len - 2; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i + 1][j + 1], Math.min(dp[i][j + 1], dp[i + 1][j]));
                }
            }
        }

        return dp[0][0];
    }

}
