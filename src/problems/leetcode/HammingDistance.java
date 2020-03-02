package problems.leetcode;

/**
 * https://leetcode.com/problems/hamming-distance/
 */
public class HammingDistance {

    public static int hammingDistance(int x, int y) {
        int i = x ^ y;

        int distance = 0;
        while (i > 0) {
            i &= i - 1;
            distance++;
        }

        return distance;
    }

    public static void main(String[] args) {
        int x = 0b0111, y = 0b1010;
        System.out.println(hammingDistance(x, y));
    }

}
