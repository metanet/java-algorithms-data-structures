package problems.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/evaluate-reverse-polish-notation/
 */
public class EvaluateReversePolishNotation {

    public static void main(String[] args) {
        String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(evalRPN(tokens));
    }


    public static int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (String token : tokens) {
            Operator operator = Operator.parse(token);
            if (operator != null) {
                Integer right = stack.removeLast();
                Integer left = stack.removeLast();
                stack.addLast(calculate(operator, left, right));
            } else {
                stack.addLast(Integer.valueOf(token));
            }
        }

        return stack.removeLast();
    }

    private static int calculate(Operator operator, int left, int right) {
        switch (operator) {
            case ADD:
                return left + right;
            case SUBTRACT:
                return left - right;
            case MULTIPLY:
                return left * right;
            case DIVIDE:
                return left / right;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    private enum Operator {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE;

        static Operator parse(String token) {
            switch (token) {
                case "+":
                    return ADD;
                case "-":
                    return SUBTRACT;
                case "*":
                    return MULTIPLY;
                case "/":
                    return DIVIDE;
                default:
                    return null;
            }
        }
    }

}
