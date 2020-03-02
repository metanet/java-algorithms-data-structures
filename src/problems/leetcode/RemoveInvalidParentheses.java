package problems.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/remove-invalid-parentheses/
 */
public class RemoveInvalidParentheses {

    private Set<String> validStrs = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {
        if (isValid(s, new boolean[s.length()])) {
            return Collections.singletonList(s);
        }

        int[] deletionCounts = deletionCounts(s);
        int left = deletionCounts[0];
        int right = deletionCounts[1];

        for (int startIndex = 0; startIndex < s.length(); startIndex++) {
            removeInvalidParentheses(s, left, right, startIndex, new boolean[s.length()]);
        }

        return new ArrayList<>(validStrs);
    }

    public void removeInvalidParentheses(String s, int leftDeletionCount, int rightDeletionCount, int i, boolean[] deleted) {
        if ((leftDeletionCount == 0 && rightDeletionCount == 0) || i == s.length()) {
            if (isValid(s, deleted)) {
                validStrs.add(toString(s, deleted));
            }
            return;
        }

        char c = s.charAt(i);
        if (c != '(' && c != ')') {
            removeInvalidParentheses(s, leftDeletionCount, rightDeletionCount, i + 1, deleted);
            return;
        }

        if (c == '(') {
            if (leftDeletionCount > 0) {
                deleted[i] = true;
                for (int j = i + 1; j <= s.length(); j++) {
                    removeInvalidParentheses(s, leftDeletionCount - 1, rightDeletionCount, j, deleted);
                }
                deleted[i] = false;
            } else if (rightDeletionCount > 0) {
                removeInvalidParentheses(s, leftDeletionCount, rightDeletionCount, i + 1, deleted);
            }
        } else if (rightDeletionCount > 0) {
            deleted[i] = true;
            for (int j = i + 1; j <= s.length(); j++) {
                removeInvalidParentheses(s, leftDeletionCount, rightDeletionCount - 1, j, deleted);
            }
            deleted[i] = false;
        } else if (leftDeletionCount > 0) {
            removeInvalidParentheses(s, leftDeletionCount, rightDeletionCount, i + 1, deleted);
        }
    }

    private boolean isValid(String s, boolean[] deleted) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (deleted[i]) {
                continue;
            }

            char c = s.charAt(i);
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
                if (count < 0) {
                    break;
                }
            }
        }

        return count == 0;
    }

    private String toString(String s, boolean[] deleted) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (deleted[i]) {
                continue;
            }

            sb.append(s.charAt(i));
        }

        return sb.toString();
    }

    private int[] deletionCounts(String s) {
        int left = 0, right = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                left++;
            } else if (c == ')') {
                left--;
                if (left < 0) {
                    right++;
                    left = 0;
                }
            }
        }

        return new int[]{left, right};
    }

    // (()())

    public static void main(String[] args) {
        RemoveInvalidParentheses sol = new RemoveInvalidParentheses();
        //        String input = "()())()";
        String input = "(a)())()";
        System.out.println(Arrays.toString(sol.deletionCounts(input)));
        List<String> result = sol.removeInvalidParentheses(input);
        for (String s : result) {
            System.out.println("> " + s);
        }
    }

}
