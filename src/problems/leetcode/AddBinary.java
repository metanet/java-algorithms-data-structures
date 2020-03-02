package problems.leetcode;

/**
 * https://leetcode.com/problems/add-binary/
 */
public class AddBinary {

    public static void main(String[] args) {
        String a = "11";
        String b = "11";
        System.out.println(addBinary(a, b));
    }

    public static String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        int carry = 0;
        for (int i = 0, len = Math.max(a.length(), b.length()); i < len; i++) {
            int na = i < a.length() ? a.charAt(a.length() - 1 - i) - '0' : 0;
            int nb = i < b.length() ? b.charAt(b.length() - 1 - i) - '0' : 0;

            int sum = na + nb + carry;
            if (sum == 2) {
                sum = 0;
                carry = 1;
            } else if (sum == 3) {
                sum = 1;
                carry = 1;
            } else {
                carry = 0;
            }

            result.append(sum);
        }

        if (carry > 0) {
            result.append(carry);
        }

        return result.reverse().toString();
    }

}
