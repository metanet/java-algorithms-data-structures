package problems.leetcode;

/**
 * https://leetcode.com/problems/number-of-1-bits/
 */
public class NumberOf1Bits {

    // you need to treat n as an unsigned value
    public static int hammingWeight(int n) {
        int weight = 0;
        while (n != 0) {
            n &= n - 1;
            weight++;
        }

        return weight;
    }

    public static void main(String[] args) {
//        int n = 0b00000000000000000000000000001011;
        int n = 0b11111111111111111111111111111101;
        System.out.println(hammingWeight(n));
    }

}
