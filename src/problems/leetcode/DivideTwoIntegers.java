package problems.leetcode;

/**
 * https://leetcode.com/problems/divide-two-integers/
 */
public class DivideTwoIntegers {

    public static void main(String[] args) {
        System.out.println(divide(2147483647, -2147483648));
        System.out.println(divide(-2147483648, -1));
        System.out.println(divide(-2147483648, -2147483648));

        // -2147483648 / -1 = 2147483648 which is greater than 2^32 - 1 
    }

    // Note: Assume we are dealing with an environment that could only store
    // integers within the 32-bit signed integer range: [−2^31, 2^31 − 1]. 
    // For this problem, if the quotient is strictly greater than 2^31 - 1, 
    // then return 2^31 - 1, and if the quotient is strictly less than -2^31, 
    // then return -2^31.

    // runtime: O(lgN)
    // space: O(1)
    public static int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        } 

        int divid = Math.abs(dividend), divis = Math.abs(divisor), res = 0;
        while (divid - divis >= 0) {
            int x = 0; // 1, 2, 4, 8
            for (; divid - (divis << x << 1) >= 0; x++) {
            }
            res += 1 << x;
            divid -= divis << x;
        }

        return (dividend > 0) == (divisor > 0) ? res : -res;
    }

    public static int divideWith64BitInteger(int dividend, int divisor) {
        long divid = dividend, divis = divisor, quot = 0;
        divid = Math.abs(divid);
        divis = Math.abs(divis);

        while (divid >= divis) {
            long q = 1, d = divis;
            while (d + d <= divid) {
                d = d + d;
                q = q + q;
            }

            quot += q;
            divid -= d;
        }

        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) {
            quot = -quot;
        }

        quot = Math.min(quot, Integer.MAX_VALUE);
        return (int) quot;
    }

}
