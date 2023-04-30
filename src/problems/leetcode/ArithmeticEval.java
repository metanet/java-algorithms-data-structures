package problems.leetcode;

public class ArithmeticEval {

    // Given a string of an arithmetic expression, composed of numbers, 
    // '+' and '*', calculate the result -
    //
    // 2 + 4 * 5 * 7
    //
    // 1 + 2 + 5
    //
    // 2 + 4 * 5 + 7

    static int eval(String exp) {
        int result = 0, current = 1;
        for (String token : exp.split(" ")) {
            if (token.isBlank()) {
                continue;
            } else if (token.equals("+")) {
                result += current;
                current = 1;
            } else if (!token.equals("*")) {
                current *= Integer.parseInt(token);
            }
        }

        return result + current;
    }

    public static void main(String[] args) {
        String exp1 = "2 + 4 * 5 + 7";
        String exp2 = "1 + 2 + 3";
        String exp3 = "1 + 2";
        String exp4 = "2 * 4";
        String exp5 = "24";
        System.out.println(eval(exp1));
        System.out.println(eval(exp2));
        System.out.println(eval(exp3));
        System.out.println(eval(exp4));
        System.out.println(eval(exp5));
    }
}
