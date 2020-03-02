package problems.leetcode;

/**
 * https://leetcode.com/problems/divide-two-integers/
 */
public class DivideTwoIntegers {

    public static int divide(int dividend, int divisor) {
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

    public static void main(String[] args) {
        int dividend = 300;
        int divisor = 5;
        System.out.println(divide(dividend, divisor));
    }

}
