package problems.leetcode;

/**
 * https://leetcode.com/problems/reverse-integer/solution/
 */
public class ReverseInteger {

    public static void main(String[] args) {
        int x = 34056;
        System.out.println(reverse(x));
    }

    public static int reverse(int x) {
        long num = Math.abs(x), reverseNum = 0, j = 10, p = 10;

        for (int i = 0; i < 11; i++) {
            long digit = (num % j) / Math.max((j / 10), 1);
            if (digit > 0) {
                reverseNum = reverseNum * p + digit;
                p = 10;
            } else {
                p *= 10;
            }

            j *= 10;
        }

        if (x < 0) {
            reverseNum = -reverseNum;
        }

        return (reverseNum > Integer.MAX_VALUE || reverseNum < Integer.MIN_VALUE) ? 0 : (int) reverseNum;
    }

}
