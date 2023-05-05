package problems.leetcode;

/**
 * https://leetcode.com/problems/reverse-integer/solution/
 */
public class ReverseInteger {

    public static void main(String[] args) {
        // System.out.println(reverse(34056));
        System.out.println(reverse(1534236469));
    }

    // runtime: O(N)
    // space: O(1)
    public static int reverse(int x) {
        if (x == 0) {
            return x;
        }

        int n = x;
        if (n < 0) {
            n *= -1;
        }
        int y = 0;
        while (n > 0) {
            if (y > 0 && Integer.MAX_VALUE / y < 10) {
                return 0;
            }

            int d = n % 10;
            y = d + y * 10;
            n /= 10;
        }
        if (x < 0) {
            y *= -1;
        }

        return y;
    }

}
