package problems.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * https://leetcode.com/problems/rotting-oranges/
 */
public class RottingOranges {

    public static void main(String[] args) {
        int[][] grid = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        System.out.println(orangesRotting(grid));
    }

    private static final int[] ROW_MOVES = new int[]{-1, 0, 1, 0};
    private static final int[] COL_MOVES = new int[]{0, -1, 0, 1};

    public static int orangesRotting(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;

        // queue : all starting cells with rotten oranges
        Queue<Rot> queue = new ArrayDeque<>();
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                if (grid[row][col] == 2) {
                    queue.add(new Rot(row, col, 0));
                }
            }
        }

        int days = 0;
        while (!queue.isEmpty()) {
            Rot rot = queue.remove();

            for (int k = 0; k < 4; ++k) {
                int neighbourRow = rot.row + ROW_MOVES[k], neighbourCol = rot.col + COL_MOVES[k];
                if (0 <= neighbourRow && neighbourRow < rows && 0 <= neighbourCol && neighbourCol < cols && grid[neighbourRow][neighbourCol] == 1) {
                    grid[neighbourRow][neighbourCol] = 2;
                    int neighbourRotDay = rot.day + 1;
                    queue.add(new Rot(neighbourRow, neighbourCol, neighbourRotDay));
                    days = neighbourRotDay;
                }
            }
        }

        for (int[] row : grid) {
            for (int v : row) {
                if (v == 1) {
                    return -1;
                }
            }
        }

        return days;
    }

    private static class Rot {
        final int row, col, day;

        Rot(int row, int col, int day) {
            this.row = row;
            this.col = col;
            this.day = day;
        }
    }

}
