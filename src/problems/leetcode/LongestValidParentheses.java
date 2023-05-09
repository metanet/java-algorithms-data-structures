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
        // String s = ")(((((()())()()))()(()))(";
        String s = "(()(((()";
        System.out.println(longestValidParenthesesStack(s));
        System.out.println(longestValidParenthesesDP(s));
    }

    // runtime: O(N)
    // space: O(N)
    public static int longestValidParenthesesDP(String s) {
        //     (  (  )  (  )  )
        // dp: 0, 0, 0, 0, 0, 0
        // i = 0
        // i = 1
        // i = 2
        // dp: 0, 0, 2, 0, 0, 0
        // i = 3
        // i = 4
        // dp: 0, 0, 2, 0, 2, 0
        // dp: 0, 0, 2, 0, 4, 0
        // i = 5
        // dp: 0, 0, 2, 0, 4, 6

        int maxLen = 0;
        // i'th element represents the length of the longest valid substring ending at i'th index. 
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                continue;
            } else if (s.charAt(i - 1) == '(') {
                // here we finished a valid pair
                dp[i] = 2;
                if (i >= 2) {
                    // if there was a valid pair at i - 2, take it into account
                    dp[i] += dp[i - 2];
                }
            } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                // (()()) <-
                dp[i] = dp[i - 1] + 2;
                if ((i - dp[i - 1]) >= 2) {
                    // ()(()()) <-
                    // 0200204
                    // i=7
                    // 02002048
                    dp[i] += dp[i - dp[i - 1] - 2];
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    // runtime: O(N)
    // space: O(N)
    public static int longestValidParenthesesStack(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }

        Deque<Integer> stack = new ArrayDeque<>();
        int longest = 0;
        stack.addLast(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.addLast(i);
            } else {
                stack.removeLast();
                if (stack.isEmpty()) {
                    stack.addLast(i);
                } else {
                    longest = Math.max(longest, i - stack.peekLast());
                }
            }
        }

        return longest;
    }

    // runtime: O(N)
    // space: O(N)
    public static int longestValidParenthesesStack2(String s) {
        // "(()(((()"
        // stack: 
        // i = 0
        // open = 1
        // stack = (
        // i = 1
        // open = 2
        // stack = ((
        // i = 2
        // open = 1
        // stack = (()
        // i = 3
        // open = 2
        // stack = (()(
        // i = 4
        // open = 3
        // stack = (()((
        // i = 5
        // open = 4
        // stack = (()(((
        // i = 6
        // open = 5
        // stack = (()((((
        // i = 7
        // open = 4
        // stack = (()(((

        if (s == null || s.length() < 2) {
            return 0;
        }

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
