package problems.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/generate-parentheses/
 */
public class GenerateParentheses {

    public static void main(String[] args) {
        int n = 3;
        List<String> list = generateParenthesis(n);
        for (String s : list) {
            System.out.println(s);
        }
    }

    /*

     Output
    "(((())))", +
    "((()))()", +
    "()((()))", +
    "((())())", +
    "(())()()", +
    "()(())()", +
    "(()(()))", +
    "()()(())", +
    "((()()))", +
    "(()())()", +
    "()(()())", +
    "(()()())", +
    "()()()()"  +

    Expected
    "(((())))", +
    "((()()))", +
    "((())())", +
    "((()))()", +
    "(()(()))", +
    "(()()())", +
    "(()())()", +
    "(())(())", ?
    "(())()()", +
    "()((()))", +
    "()(()())", +
    "()(())()", +
    "()()(())", +
    "()()()()"  +

     */

    public static List<String> generateParenthesis(int n) {
        List<String> results = new ArrayList<>();
        generateParenthesis(results, new StringBuilder(), 0, 0, n);
        return results;
    }

    private static void generateParenthesis(List<String> results, StringBuilder sb, int open, int close, int n) {
        if (sb.length() == n * 2) {
            results.add(sb.toString());
            return;
        }

        if (open < n) {
            sb.append('(');
            generateParenthesis(results, sb, open + 1, close, n);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (close < open) {
            sb.append(')');
            generateParenthesis(results, sb, open, close + 1, n);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

}
