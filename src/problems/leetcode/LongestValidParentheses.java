package problems.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/longest-valid-parentheses/
 */
public class LongestValidParentheses {

    // "()(()" expected : 2
    public static void main(String[] args) {
        //        String s = "(((()())";
        //        String s = "()(()";
        String s = ")(((((()())()()))()(()))(";
        System.out.println(longestValidParentheses(s));
        System.out.println(longestValidParenthesesDP(s));
    }

    public static int longestValidParenthesesDP(String s) {
        int maxLen = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                continue;
            }

            if (s.charAt(i - 1) == '(') {
                dp[i] = 2;
                if (i >= 2) {
                    dp[i] += dp[i - 2];
                }
            } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                dp[i] = dp[i - 1] + 2;
                if ((i - dp[i - 1]) >= 2) {
                    dp[i] += dp[i - dp[i - 1] - 2];
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    public static int longestValidParentheses(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        int maxLen = 0, open = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.addLast(ch);
                open++;
            } else if (open > 0) {
                open--;
                stack.addLast(ch);
            } else {
                maxLen = Math.max(maxLen, stack.size());
                stack.clear();
            }
        }

        int len = 0, right = 0;
        while (stack.size() > 0) {
            char ch = stack.removeLast();
            if (ch == ')') {
                right++;
                len++;
            } else if (right > 0) {
                right--;
                len++;
            } else {
                maxLen = Math.max(maxLen, len);
                right = 0;
                len = 0;
            }
        }

        maxLen = Math.max(maxLen, len);

        return maxLen;
    }

}
