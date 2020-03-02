package problems.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/unique-binary-search-trees/
 * <p>
 * dynamic programming
 */
public class UniqueBinarySearchTrees {

    private int[] counts;

    public int numTrees(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1 || n == 2) {
            return n;
        } else if (n == 3) {
            return 5;
        }

        counts = new int[n + 1];
        Arrays.fill(counts, -1);
        counts[0] = 1;
        counts[1] = 1;
        counts[2] = 2;
        counts[3] = 5;

        return numTrees2(n);
    }

    private int numTrees2(int n) {
        if (counts[n] != -1) {
            return counts[n];
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {
            count += numTrees2(i - 1) * numTrees2(n - i);
        }

        counts[n] = count;

        return count;
    }

    public int numTreesDP(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        int[] counts = new int[n + 1];
        counts[0] = 1; // empty tree
        counts[1] = 1; // tree with only root

        for (int i = 2; i <= n; i++) {
            int count = 0;
            for (int j = 1; j <= i; j++) {
                count += counts[j - 1] * counts[i - j];
            }
            counts[i] = count;
        }

        return counts[n];
    }


    public static void main(String[] args) {
        int n = 10;
        System.out.println(new UniqueBinarySearchTrees().numTrees(n));
        System.out.println(new UniqueBinarySearchTrees().numTreesDP(n));
    }

}
