package problems.leetcode;

/**
 * https://leetcode.com/problems/multiply-strings/
 */
public class MultiplyStrings {

    public static void main(String[] args) {
        String num1 = "12512";
        String num2 = "0";

        String res = multiply(num1, num2);
        System.out.println(res);
    }

    public static String multiply(String num1, String num2) {
        num1 = new StringBuilder(num1).reverse().toString();
        num2 = new StringBuilder(num2).reverse().toString();
        int[] result = new int[num1.length() + num2.length()];

        for (int i = 0; i < num2.length(); i++) {
            int n2 = num2.charAt(i) - '0', carry = 0;
            for (int j = 0; j < num1.length(); j++) {
                int n1 = num1.charAt(j) - '0';
                int mult = n1 * n2 + carry + result[i + j];
                result[i + j] = mult % 10;
                carry = mult / 10;
            }

            result[i + num1.length()] += carry;
        }

        StringBuilder s = new StringBuilder();
        for (int i = result.length - 1; i >= 0; i--) {
            if (result[i] == 0 && s.length() == 0) {
                continue;
            }

            s.append(result[i]);
        }

        if (s.length() == 0) {
            s.append(0);
        }

        return s.toString();
    }

    /*
     *    45
     *    17
     * x----
     *   315
     *   45
     * +----
     *   765
     *
     *
     *    45
     *    37
     * x----
     * 513
     *  531
     * 5661
     *
     *    45
     *    37
     * x----
     * 5661
     *
     */

}
