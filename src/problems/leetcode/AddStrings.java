package problems.leetcode;

/**
 * https://leetcode.com/problems/add-strings/
 */
public class AddStrings {

    public static void main(String[] args) {
        String num1 = "123";
        String num2 = "99";

        System.out.println(addStrings(num1, num2));
    }

    public static String addStrings(String num1, String num2) {
        num1 = new StringBuilder(num1).reverse().toString();
        num2 = new StringBuilder(num2).reverse().toString();
        StringBuilder res = new StringBuilder();

        int carry = 0;
        for (int i = 0, j = Math.max(num1.length(), num2.length()); i < j; i++) {
            int n1 = i < num1.length() ? num1.charAt(i) - '0' : 0, n2 = i < num2.length() ? num2.charAt(i) - '0' : 0;
            int sum = carry + n1 + n2;
            if (sum >= 10) {
                sum -= 10;
                carry = 1;
            } else {
                carry = 0;
            }
            res.append(sum);
        }

        if (carry > 0) {
            res.append(carry);
        }

        return res.reverse().toString();
    }

}
