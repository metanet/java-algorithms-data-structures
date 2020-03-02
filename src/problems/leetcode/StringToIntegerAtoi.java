package problems.leetcode;

/**
 * https://leetcode.com/problems/string-to-integer-atoi/
 */
public class StringToIntegerAtoi {

    public static void main(String[] args) {
        String s = "9223372036854775808";
        System.out.println(myAtoi(s));
    }

    public static int myAtoi(String str) {
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
