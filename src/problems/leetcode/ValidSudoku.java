package problems.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/valid-sudoku/
 */
public class ValidSudoku {

    static final int BOARD_SIZE = 9;
    static final int GRID_SIZE = 3;

    public static boolean isValidSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9) {
            return false;
        }

        boolean[] found = new boolean[BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            // validate rows
            Arrays.fill(found, false);
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (!checkCell(board[i][j], found)) {
                    return false;
                }
            }

            // validate cols
            Arrays.fill(found, false);
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (!checkCell(board[j][i], found)) {
                    return false;
                }
            }

            // validate grids
            Arrays.fill(found, false);
            for (int j = 0, r = GRID_SIZE * (i / GRID_SIZE), c = GRID_SIZE * (i % GRID_SIZE); j < BOARD_SIZE; j++) {
                if (!checkCell(board[r + j / GRID_SIZE][c + (j % GRID_SIZE)], found)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean checkCell(char c, boolean[] found) {
        if (c == '.') {
            return true;
        }
        int num = c - '1';
        if (found[num]) {
            return false;
        }

        found[num] = true;
        return true;
    }

    public static void main(String[] args) {
        char[][] board = { { '.', '.', '.', '.', '.', '.', '5', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '9', '3', '.', '.', '2', '.', '4', '.', '.' },
                { '.', '.', '7', '.', '.', '.', '3', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '3', '4', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '3', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '5', '2', '.', '.' } };

        System.out.println(isValidSudoku(board));
    }

}
