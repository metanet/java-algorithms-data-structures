package problems.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/basic-calculator/
 */
public class BasicCalculator {

    /*
    "(1-(4+5+2)+3)+(6+8)"
     */

    public static void main(String[] args) {
        String s = "(1-(4+5+2)+3)";
//        String s = "(1-3)+2";
        System.out.println(calculate(s));
    }

    private static class Holder {
        int val;
        int sign;

        Holder(int val, int sign) {
            this.val = val;
            this.sign = sign;
        }
    }

    public static int calculate(String s) {
        int sign = 1, left = 0, right = 0;
        Deque<Holder> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                right *= 10;
                right += c - '0';
            } else if (c == '+') {
                left += sign * right;
                sign = 1;
                right = 0;
            } else if (c == '-') {
                left += sign * right;
                sign = -1;
                right = 0;
            } else if (c == '(') {
                stack.addLast(new Holder(left, sign));
                left = 0;
                sign = 1;
            } else if (c == ')') {
                left += sign * right;
                Holder holder = stack.removeLast();
                left = holder.val + holder.sign * left;
                sign = 1;
                right = 0;
            }
        }

        left += sign * right;

        return left;
    }

}
