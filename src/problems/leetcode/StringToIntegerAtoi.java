package problems.leetcode;

/**
 * https://leetcode.com/problems/string-to-integer-atoi/
 */
public class StringToIntegerAtoi {

    public static void main(String[] args) {
        System.out.println(myAtoi("9223372036854775808"));
    }

    // runtime: O(N)
    // space: O(1)
    public static int myAtoi(String s) {
        if (s == null || s.isBlank()) {
            return 0;
        }

        s = s.trim();
        int sign = 1;
        int start = 0;
        if (s.charAt(0) == '-') {
            sign = -1;
            start = 1;
        } else if (s.charAt(0) == '+') {
            start = 1;
        }

        long n = 0;
        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!Character.isDigit(c)) {
                break;
            }

            n = n * 10 + (c - '0');
            if (n > Integer.MAX_VALUE) {
                break;
            }
        }
        n *= sign;

        return (int) Math.max(Math.min(n, Integer.MAX_VALUE), Integer.MIN_VALUE);
    }

    public static int myAtoi2(String str) {
        int sign = 1, i = 0;
        long num = 0;
        while (i < str.length() && str.charAt(i) == ' ') {
            i++;
        }

        if (i < str.length()) {
            if (str.charAt(i) == '-') {
                sign = -1;
                i++;
            } else if (str.charAt(i) == '+') {
                i++;
            }
        }


        while (i < str.length()) {
            char c = str.charAt(i);
            if (!Character.isDigit(c)) {
                break;
            }

            num = 10 * num + c - '0';
            if (num > Integer.MAX_VALUE) {
                break;
            }

            i++;
        }

        num *= sign;

        num = Math.min(num, Integer.MAX_VALUE);
        num = Math.max(num, Integer.MIN_VALUE);

        return (int) num;
    }

}
