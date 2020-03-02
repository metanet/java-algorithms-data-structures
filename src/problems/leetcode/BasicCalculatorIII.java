package problems.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/basic-calculator-iii/
 */
public class BasicCalculatorIII {

    public static void main(String[] args) {
//        String expression = "1*2-3/4+5*6-7*8+9/10";
//        String expression = "2-0+30-56+0";
//        String expression = "-1+4*3/3/3";
//        String expression = "0-2147483648";
        String expression = "1+2+3*4/2+5";

        int result = new BasicCalculatorIII().calculate(expression);
        System.out.println(result);

        System.out.println(hasPrecedence('+', '+'));
        System.out.println(hasPrecedence('+', '/'));
        System.out.println(hasPrecedence('/', '/'));
        System.out.println(hasPrecedence('/', '+'));
    }

    private int i;
    private String s;

    public int calculate(String s) {
        this.s = s.replaceAll(" ", "");
        return (int) calculate();
    }

    private long calculate() {
        // used as stacks
        Deque<Long> operands = new ArrayDeque<>();
        Deque<Character> operators = new ArrayDeque<>();

        while (hasNext()) {
            if (closeParentheses()) {
                break;
            } else if (hasNextNum()) {
                operands.addLast(nextNum());
            } else if (openParentheses()) {
                operands.addLast(calculate());
            } else {
                char operator = nextOperator();
                while (operators.size() > 0 && hasPrecedence(operator, operators.peekLast())) {
                    // normally the problem says there are no negative numbers
                    // but submissions have negative numbers
                    operands.addLast(eval(operands.removeLast(), operands.size() > 0 ? operands.removeLast() : 0, operators.removeLast()));
                }

                operators.addLast(operator);
            }
        }

        // normally the problem says there are no negative numbers
        // but submissions have negative numbers
        while (operators.size() > 0) {
            operands.addLast(eval(operands.removeLast(), operands.size() > 0 ? operands.removeLast() : 0, operators.removeLast()));
        }

        return operands.removeLast();
    }

    private boolean hasNext() {
        return i < s.length();
    }

    private boolean closeParentheses() {
        if (s.charAt(i) == ')') {
            i++;

            return true;
        }

        return false;
    }

    private boolean openParentheses() {
        if (s.charAt(i) == '(') {
            i++;

            return true;
        }

        return false;
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

}
