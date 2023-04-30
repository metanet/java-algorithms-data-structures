package problems.leetcode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class TwoStackEval {
    
    
    private static void eval(Stack<String> ops, Stack<Double> vals) {
        if (vals.size() < 2 || ops.isEmpty()) {
            throw new IllegalArgumentException("invalid expression");
        }

        double val2 = vals.pop();
        double val1 = vals.pop();
        String op = ops.pop();

        double result;
        if (op.equals("+")) {
            result = val1 + val2;
        } else if (op.equals("-")) {
            result = val1 - val2;
        } else if (op.equals("*")) {
            result = val1 * val2;
        } else if (op.equals("/")) { 
            result = val1 / val2;
        } else {
            throw new IllegalArgumentException("invalid op: " + op);
        }
        vals.push(result);
    }

    public static double eval(String str) {
    
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();

        for (String token : str.split(" ")) {
            if (token.isBlank()) {
                continue;
            }

            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                ops.push(token);
            } else if (token.equals("(")) {
                // ignore (
                continue;
            } else if (token.equals(")")) {
                eval(ops, vals);
            } else {
                vals.push(Double.parseDouble(token));
            }
        }

        while (vals.size() > 1) {
            eval(ops, vals);
        }

        return !vals.empty() ? vals.pop() : 0;
    }

    public static void main(String[] args) {
        String exp1 = "1 + 2 + 3";
        String exp2 = "4 + ( 2 - 3 )";
        String exp3 = "( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )";
        System.out.println(eval(exp1));
        System.out.println(eval(exp2));
        System.out.println(eval(exp3));
    }


}
