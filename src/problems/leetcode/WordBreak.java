package problems.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/word-break/
 */
public class WordBreak {

    public static void main(String[] args) {
        String input = "catsanddog";
        List<String> dict = Arrays.asList("cats", "dog", "sand", "and", "cat");
        System.out.println(wordBreakDP(input, dict));
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        return wordBreak(new HashSet<>(wordDict), s, 0, new boolean[s.length()]);
    }

    private static boolean wordBreak(Set<String> dictionary, String str, int i, boolean[] invalid) {
        if (i == str.length()) {
            return true;
        } else if (invalid[i]) {
            return false;
        }

        for (int j = i + 1; j <= str.length(); j++) {
            if (dictionary.contains(str.substring(i, j)) && wordBreak(dictionary, str, j, invalid)) {
                return true;
            }
        }

        invalid[i] = true;
        return false;
    }

    public static boolean wordBreakDP(String input, List<String> dictionary) {
        if (dictionary == null || dictionary.size() == 0) {
            return false;
        }

        Set<String> set = new HashSet<>(dictionary);

        int len = input.length();
        // dp[i] = true means input.substring(0, i) is valid
        boolean[] dp = new boolean[len + 1];
        for (int i = 0; i <= len; i++) {
            if (set.contains(input.substring(0, i))) {
                dp[i] = true;
            }
        }

        for (int i = 0; i <= len; i++) {
            for (int j = 0; j <= i; j++) {
                if (dp[j] && set.contains(input.substring(j, i))) {
                    // if input.substring(0, j) is valid and input.substring(j, i) is also valid
                    // then dp[i] is valid.
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[len];
    }


}
