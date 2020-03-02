package problems.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/valid-parentheses/
 */
public class ValidParentheses {

    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '[' || ch == '(' || ch == '{') {
                stack.addLast(ch);
            } else if (stack.isEmpty()) {
                return false;
            } else {
                char open = stack.removeLast();
                if ((ch == ']' && open != '[') || (ch == ')' && open != '(') || (ch == '}' && open != '{')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

}
