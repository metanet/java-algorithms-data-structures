package problems.leetcode;

/**
 * https://leetcode.com/problems/power-of-two/
 */
public class PowerOfTwo {

    public static boolean isPowerOfTwo(int n) {
        return n > 0 && ((n & -n) == n);
    }

    public static void main(String[] args) {
        System.out.println(isPowerOfTwo(2));
        System.out.println(isPowerOfTwo(Integer.MAX_VALUE));
        System.out.println(isPowerOfTwo(Integer.MIN_VALUE));
    }

}
