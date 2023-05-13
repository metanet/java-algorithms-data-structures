package problems.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/unique-paths/
 */
public class UniquePaths {

    public static void main(String[] args) {
        int m = 3;
        int n = 7;
        System.out.println(uniquePaths(m, n));
    }

    // runtime: O(N * M)
    // space: O(N * M)
    public static int uniquePaths(int m, int n) {
        if (m == 1 && n == 1) {
            return 1;
        }

        int[][] paths = new int[m][n];
        for (int i = 0; i < m; i++) {
            paths[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            paths[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                paths[i][j] = paths[i - 1][j] + paths[i][j - 1];
            }
        }

        // for (int[] row : paths) {
        //  System.out.println(Arrays.toString(row));
        // }

        return paths[m - 1][n - 1];
    }

    /*
     * 
     * x . .
     * . . .
     * . . y
     * 
     * r r d d
     * r d r d
     * r d d r
     * d d r d
     * 
     */

}
