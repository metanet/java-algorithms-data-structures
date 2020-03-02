package problems.leetcode;

/**
 * https://leetcode.com/problems/minimum-path-sum/
 */
public class MinimumPathSum {

    public static void main(String[] args) {
        int[][] grid = {{1, 3, 1, 4, 1}, {1, 5, 2, 1, 6}, {4, 2, 3, 2, 5}};
        System.out.println(minPathSum(grid));
    }

    public static int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = n - 1; i >= 0; i--) {
            dp[m - 1][i] = grid[m - 1][i] + dp[m - 1][i + 1];
        }

        for (int i = m - 1; i >= 0; i--) {
            dp[i][n - 1] = grid[i][n - 1] + dp[i + 1][n - 1];
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
            }
        }

        return dp[0][0];
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
