package problems.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/game-of-life/
 */
public class GameOfLife {

    public static void main(String[] args) {
        int[][] board = {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        gameOfLife(board);
        for (int[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static void gameOfLife(int[][] board) {
        int[][] updatedBoard = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int[] counts = count(board, i, j);
                int newState;
                if (counts[1] == 3) {
                    newState = 1;
                } else if (counts[1] < 2 || counts[1] > 3) {
                    newState = 0;
                } else {
                    newState = board[i][j];
                }
                updatedBoard[i][j] = newState;
            }
        }

        for (int i = 0; i < board.length; i++) {
            System.arraycopy(updatedBoard[i], 0, board[i], 0, board[i].length);
        }
    }

    private static int[] count(int[][] board, int i, int j) {
        int[] counts = new int[2];
        count(board, i + 1, j, counts);
        count(board, i - 1, j, counts);
        count(board, i + 1, j + 1, counts);
        count(board, i - 1, j - 1, counts);
        count(board, i, j + 1, counts);
        count(board, i - 1, j + 1, counts);
        count(board, i, j - 1, counts);
        count(board, i + 1, j - 1, counts);
        return counts;
    }

    private static void count(int[][] board, int i, int j, int[] counts) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length) {
            return;
        }

        if (board[i][j] == 0) {
            counts[0]++;
        } else {
            counts[1]++;
        }
    }

}
