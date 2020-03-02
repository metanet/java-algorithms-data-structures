package problems.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/minesweeper/
 */
public class Minesweeper {

    public static void main(String[] args) {
        char[][] board = {{'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'M', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E'}};
        int[] click = {3, 0};
        updateBoard(board, click);

        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static char[][] updateBoard(char[][] board, int[] click) {
        int i = click[0], j = click[1];
        if (board[i][j] == 'M') {
            board[i][j] = 'X';
            return board;
        }

        reveal(board, i, j);

        return board;
    }

    private static void reveal(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length) {
            return;
        } else if (board[i][j] == 'M') {
            board[i][j] = 'X';
            return;
        } else if (board[i][j] == 'B') {
            return;
        }

        int adjacentMineCount = adjacentMineCount(board, i, j);
        if (adjacentMineCount != 0) {
            board[i][j] = (char) ('0' + adjacentMineCount);
            return;
        }

        board[i][j] = 'B';

        reveal(board, i + 1, j);
        reveal(board, i - 1, j);
        reveal(board, i, j + 1);
        reveal(board, i, j - 1);
        reveal(board, i + 1, j + 1);
        reveal(board, i + 1, j - 1);
        reveal(board, i - 1, j + 1);
        reveal(board, i - 1, j - 1);
    }

    private static int adjacentMineCount(char[][] board, int i, int j) {
        int mineCount = 0;
        mineCount += isMine(board, i + 1, j);
        mineCount += isMine(board, i - 1, j);
        mineCount += isMine(board, i, j + 1);
        mineCount += isMine(board, i, j - 1);
        mineCount += isMine(board, i + 1, j + 1);
        mineCount += isMine(board, i + 1, j - 1);
        mineCount += isMine(board, i - 1, j + 1);
        mineCount += isMine(board, i - 1, j - 1);
        return mineCount;
    }

    private static int isMine(char[][] board, int i, int j) {
        return (i >= 0 && i < board.length && j >= 0 && j < board[i].length && board[i][j] == 'M') ? 1 : 0;
    }

}
