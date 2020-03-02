package problems.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/basic-calculator-ii/
 */
public class BasicCalculatorII {

    private int i;
    private String s;

    public int calculate(String s) {
        this.s = s.replaceAll(" ", "");

        // used as stacks
        Deque<Long> operands = new ArrayDeque<>();
        Deque<Character> operators = new ArrayDeque<>();

        while (hasNext()) {
            if (hasNextNum()) {
                operands.addLast(nextNum());
            } else {
                char operator = nextOperator();
                while (operators.size() > 0 && hasPrecedence(operator, operators.peekLast())) {
                    operands.addLast(eval(operands.removeLast(), operands.removeLast(),  operators.removeLast()));
                }

                operators.addLast(operator);
            }
        }

        while (operators.size() > 0) {
            operands.addLast(eval(operands.removeLast(), operands.removeLast(), operators.removeLast()));
        }

        long result = operands.removeLast();
        return (int) result;
    }

    private boolean hasNext() {
        return i < s.length();
    }

    private boolean hasNextNum() {
        return Character.isDigit(s.charAt(i));
    }

    private long nextNum() {
        int j = i;
        for (; j < s.length(); j++) {
            char ch = s.charAt(j);
            if (!(Character.isSpaceChar(ch) || Character.isDigit(ch))) {
                break;
            }
        }

        String num = s.substring(i, j);
        i = j;
        return Long.parseLong(num);
    }

    private char nextOperator() {
        int j = i;
        for (; j < s.length(); j++) {
            char ch = s.charAt(j);
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '(' || ch == ')') {
                i = j + 1;
                return ch;
            }
        }

        throw new IllegalStateException();
    }

    // operator2 has same or greater precedence than operator1
    private static boolean hasPrecedence(char operator1, char operator2) {
        return !((operator1 == '*' || operator1 == '/') && (operator2 == '+' || operator2 == '-'));
    }

    private static long eval(long num2, long num1, char operator) {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
        }

        throw new IllegalArgumentException();
    }

    public static void main(String[] args) {
        int result = new BasicCalculatorII().calculate("3 - 6 + 5 / 2 * 4 - 1");
        //        int result = new BasicCalculatorII().calculate("3 + 6 * 4");
        System.out.println(result);
    }
}
