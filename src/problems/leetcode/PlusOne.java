package problems.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/plus-one/
 */
public class PlusOne {

    public static void main(String[] args) {
        int[] num = {9, 9, 9, 9};
        int[] added = plusOne(num);
        System.out.println(Arrays.toString(added));
    }

    public static int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) {
            return new int[]{1};
        }

        int[] result = new int[digits.length + 1];
        int carry = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int r = digits[i] + carry;
            if (r >= 10) {
                r -= 10;
                carry = 1;
            } else {
                carry = 0;
            }

            result[i + 1] = r;
        }

        if (carry > 0) {
            result[0] = carry;
            return result;
        }

        return Arrays.copyOfRange(result, 1, result.length);
    }

}
