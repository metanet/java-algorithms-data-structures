package problems.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/climbing-stairs/
 * <p>
 * dynamic programming
 */
public class ClimbingStairs {

    private int[] counts;

    public int climbStairs(int n) {
        counts = new int[n];
        Arrays.fill(counts, -1);
        return climbStairs(n, 0);
    }

    private int climbStairs(int n, int i) {
        if (i == n) {
            return 1;
        } else if (i > n) {
            return 0;
        }

        if (counts[i] != -1) {
            return counts[i];
        }

        int count = climbStairs(n, i + 1) + climbStairs(n, i + 2);
        counts[i] = count;
        return count;
    }

    public int climbStairsDP(int n) {
        int[] counts = new int[n + 2];
        counts[n] = 1;

        for (int i = n - 1; i >= 0; i--) {
            counts[i] = counts[i + 1] + counts[i + 2];
        }

        return counts[0];
    }

    public static void main(String[] args) {
        int n = 1;
        System.out.println(new ClimbingStairs().climbStairs(n));
        System.out.println(new ClimbingStairs().climbStairsDP(n));

        /*
        1 1 1 1
        1 2 1
        1 1 2
        2 1 1
        2 2
         */
    }

}
