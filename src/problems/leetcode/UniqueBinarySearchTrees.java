package problems.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/unique-binary-search-trees/
 * <p>
 * dynamic programming
 */
public class UniqueBinarySearchTrees {

    public static int numTreesDP(int n) {
        if (n < 3) {
            return Math.max(0, n);
        }

        int[] counts = new int[n + 1];
        counts[0] = 1; // empty tree
        counts[1] = 1; // tree with only root

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                counts[i] += counts[j - 1] * counts[i - j];
            }
        }

        return counts[n];
    }


    public static void main(String[] args) {
        int n = 10;
        System.out.println(numTreesDP(n));
    }

}
