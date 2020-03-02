package problems.leetcode;

/**
 * https://leetcode.com/problems/sum-of-two-integers/
 */
public class SumOfTwoIntegers {

    public static void main(String[] args) {
        int a = 3; // 011
        int b = 5; // 101
        System.out.println(getSum(a, b));
    }


    public static int getSum(int a, int b) {
        int carry = (a & b) << 1;
        int sum = a ^ b;
        return carry == 0 ? sum : getSum(carry, sum);
    }

    /*
    explanation:
    for every addition, you have to add the sum and the carry.

    decimal ex.
    2 + 5 = 7
    = 0 (carry) + 7 (sum)

    3 + 9 = 12
    = 10 (carry) + 2 (sum (which is 12mod10) )

    binary ex.
    10 + 01 = 11
    = 00(carry) + 11(sum)

    1010 + 1010 = 10100
    = 10100(carry) + 0000(sum)

    in binary:
    xor operator will give us the sum; (a^b)
    and operator followed by left shift of 1 bit will give us the carry; (a&b)<<1

    we just need to find the sum and carry of the addition and add them recursively,
    until the carry is 0 (stop case for recursion). This is guaranteed to happen
    because of the left shift (it creates 0s on the right, preventing future carry values
    to occur on the right while continually moving to the left,
    therefore eventually the carry values run out).

     */

}
