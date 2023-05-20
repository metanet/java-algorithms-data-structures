package problems.leetcode;

/**
 * https://leetcode.com/problems/number-of-1-bits/
 */
public class NumberOf1Bits {

    // you need to treat n as an unsigned value
    public static int hammingWeight(int n) {
        int c = 0;
        for (int i = 0; i < 32; i++) {
            // System.out.println(n);
            c += n & 1;
            n >>= 1;
        }
        return c;
    }

    public static void main(String[] args) {
//        int n = 0b00000000000000000000000000001011;
        int n = 0b11111111111111111111111111111101;
        System.out.println(hammingWeight(n));
    }

}
