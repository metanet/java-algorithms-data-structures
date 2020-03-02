package problems.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/remove-invalid-parentheses/
 */
public class RemoveInvalidParenthesesGood {

    private Set<String> validExpressions = new HashSet<>();

    private void recurse(String s, int i, StringBuilder expression,
                         int leftValidCount, int rightValidCount,
                         int leftInvalidCount, int rightInvalidCount) {
        // If we reached the end of the string, just check if the resulting expression is
        // valid or not and also if we have removed the total number of left and right
        // parentheses that we should have removed.
        if (i == s.length()) {
            if (leftInvalidCount == 0 && rightInvalidCount == 0) {
                this.validExpressions.add(expression.toString());
            }

            return;
        }

        char c = s.charAt(i);
        int len = expression.length();

        if ((c == '(' && leftInvalidCount > 0) || (c == ')' && rightInvalidCount > 0)) {
            // If the current char is a left parentheses and we still have invalid left parentheses,
            // or the current char is a right parentheses and we still have invalid right parentheses,
            // delete the current char and recurse to the next index
            this.recurse(s, i + 1, expression, leftValidCount, rightValidCount, leftInvalidCount - (c == '(' ? 1 : 0),
                    rightInvalidCount - (c == ')' ? 1 : 0));
        }

        expression.append(c);

        if (c != '(' && c != ')') {
            // Recurse to the next index if the current c is not a parenthesis.
            this.recurse(s, i + 1, expression, leftValidCount, rightValidCount, leftInvalidCount, rightInvalidCount);
        } else if (c == '(') {
            // We can open a left parentheses
            this.recurse(s, i + 1, expression, leftValidCount + 1, rightValidCount, leftInvalidCount, rightInvalidCount);
        } else if (rightValidCount < leftValidCount) {
            // This is a right parentheses.
            // We can use it only if there are open left parentheses
            this.recurse(s, i + 1, expression, leftValidCount, rightValidCount + 1, leftInvalidCount, rightInvalidCount);
        }

        // Delete for backtracking
        expression.deleteCharAt(len);
    }

    public List<String> removeInvalidParentheses(String s) {
        int left = 0, right = 0;

        // First, we find out the number of misplaced left and right parentheses.
        for (int i = 0; i < s.length(); i++) {
            // Simply record the left one.
            if (s.charAt(i) == '(') {
                left++;
            } else if (s.charAt(i) == ')') {
                // If we don't have a matching left, then this is a misplaced right, record it.
                right = left == 0 ? right + 1 : right;

                // Decrement count of left parentheses because we have found a right
                // which CAN be a matching one for a left.
                left = left > 0 ? left - 1 : left;
            }
        }

        this.recurse(s, 0, new StringBuilder(), 0, 0, left, right);
        return new ArrayList<>(this.validExpressions);
    }

    // (()())

    public static void main(String[] args) {
        RemoveInvalidParenthesesGood sol = new RemoveInvalidParenthesesGood();
        //        String input = "()())()";
        String input = "(a)())()";
        //        System.out.println(Arrays.toString(sol.deletionCounts(input)));
        List<String> result = sol.removeInvalidParentheses(input);
        for (String s : result) {
            System.out.println("> " + s);
        }
    }

}
