package problems.leetcode;

/**
 * https://leetcode.com/problems/longest-common-subsequence/
 * https://www.youtube.com/watch?v=NnD96abizww
 */
public class LongestCommonSubsequence {

    public static int longestCommonSubsequence(String text1, String text2) {

        /*

        if text2[i] == text2[j]
            T[i][j] = 1 + T[i-1][j-1]
        else
            T[i][j] = max(T[i-1][j], T[i][j-1])

         */

        int dp[][] = new int[text1.length() + 1][text2.length() + 1];
        int longest = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }

                if (dp[i][j] > longest) {
                    longest = dp[i][j];
                }
            }
        }

        return longest;
    }

}
