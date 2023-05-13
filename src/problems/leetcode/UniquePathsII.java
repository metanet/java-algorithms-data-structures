package problems.leetcode;

import java.util.Arrays;

// https://leetcode.com/problems/unique-paths-ii/
public class UniquePathsII {

    public static void main(String[] args) {
        // System.out.println(uniquePathsWithObstacles(new int[][] {
        // { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 }
        // }));

        System.out.println(uniquePathsWithObstacles(new int[][] {
                { 1 }
        }));
    }

    // runtime: O(N * M)
    // space: O(N * M)
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length < 1) {
            return 0;
        }

        int m = obstacleGrid.length, n = obstacleGrid[0].length;

        System.out.println("---");
        for (int[] row : obstacleGrid) {
            System.out.println(Arrays.toString(row));
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    obstacleGrid[i][j] = -1;
                }
            }
        }

        boolean obstacleSeen = false;
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == -1 || obstacleSeen) {
                obstacleGrid[i][0] = -1;
                obstacleSeen = true;
            } else {
                obstacleGrid[i][0] = 1;
            }
        }
        System.out.println("---");
        for (int[] row : obstacleGrid) {
            System.out.println(Arrays.toString(row));
        }
        obstacleSeen = false;
        for (int j = 0; j < n; j++) {
            if (obstacleGrid[0][j] == -1 || obstacleSeen) {
                obstacleGrid[0][j] = -1;
                obstacleSeen = true;
            } else {
                obstacleGrid[0][j] = 1;
            }
        }

        System.out.println("---");
        for (int[] row : obstacleGrid) {
            System.out.println(Arrays.toString(row));
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] != -1) {
                    obstacleGrid[i][j] = Math.max(0, obstacleGrid[i - 1][j]) + Math.max(0, obstacleGrid[i][j - 1]);
                }
            }
        }

        System.out.println("---");
        for (int[] row : obstacleGrid) {
            System.out.println(Arrays.toString(row));
        }

        return Math.max(obstacleGrid[m - 1][n - 1], 0);
    }

}
