package problems.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-path-sum/
 */
public class MinimumPathSum {

    public static void main(String[] args) {
        int[][] grid = {{1, 3, 1, 4, 1}, {1, 5, 2, 1, 6}, {4, 2, 3, 2, 5}};
        System.out.println(minPathSum(grid));
    }

    // runtime: O(N * M)
    // space: O(N * M)
    public static int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        for (int i = 1; i < m; i++) {
            grid[i][0] += grid[i - 1][0];
        }
        for (int j = 1; j < n; j++) {
            grid[0][j] += grid[0][j - 1];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }

        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }

        return grid[m - 1][n - 1];
    }

    /*

    Input:
    [1,3,1],
    [1,5,1],
    [4,2,1]

    Output: 7
    Explanation: Because the path 1→3→1→1→1 minimizes the sum.


    1,3,1,4,1
    1,5,2,1,6
    4,2,3,2,5

    path: 1,3,1,2,1,2,5 = 15
     */

}
