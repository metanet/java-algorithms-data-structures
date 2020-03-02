package problems.leetcode;

/**
 * https://leetcode.com/problems/number-of-islands/
 */
public class NumberOfIslands {

    int N, M;

    public int numIslands(char[][] grid) {
        int islandCount = 0;
        N = grid.length;
        if (N == 0) {
            return 0;
        }
        M = grid[0].length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == '1') {
                    ++islandCount;
                    mark(grid, i, j);
                }
            }
        }

        return islandCount;
    }

    private void mark(char[][] grid, int i, int j) {
        if (i < 0 || i >= N || j < 0 || j >= M || grid[i][j] != '1') {
            return;
        }

        grid[i][j] = '2';
        mark(grid, i - 1, j);
        mark(grid, i + 1, j);
        mark(grid, i, j + 1);
        mark(grid, i, j - 1);
    }
}
