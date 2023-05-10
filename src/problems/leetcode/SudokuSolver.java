package problems.leetcode;

// https://leetcode.com/problems/sudoku-solver/
public class SudokuSolver {

    static final int BOARD_SIZE = 9;
    static final int GRID_SIZE = 3;
    static final char EMPTY = '.';

    // runtime complexity: O(9^(NN))
    // space complexity: O(NN)
    public static void solveSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9) {
            return;
        }

        // for each row, bool state represents if a number is present
        boolean[][] rows = new boolean[BOARD_SIZE][BOARD_SIZE];
        // for each col, bool state represents if a number is present
        boolean[][] cols = new boolean[BOARD_SIZE][BOARD_SIZE];
        // for each 3x3 grid, bool state represents if a number is present
        boolean[][] grids = new boolean[BOARD_SIZE][BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; i++) {
            // populate rows
            for (int j = 0; j < BOARD_SIZE; j++) {
                char ch = board[i][j];
                if (ch != EMPTY) {
                    rows[i][ch - '1'] = true;
                }
            }
            // populate cols
            for (int j = 0; j < BOARD_SIZE; j++) {
                char ch = board[j][i];
                if (ch != EMPTY) {
                    cols[i][ch - '1'] = true;
                }
            }
            // populate grids
            for (int j = 0, r = GRID_SIZE * (i / GRID_SIZE), c = GRID_SIZE * (i % GRID_SIZE); j < BOARD_SIZE; j++) {
                char ch = board[r + j / GRID_SIZE][c + (j % GRID_SIZE)];
                if (ch != EMPTY) {
                    grids[i][ch - '1'] = true;
                }
            }
        }

        boolean success = fill(board, -1, -1, rows, cols, grids);
        assert success;
    }

    private static boolean fill(char[][] board, int prevRow, int prevCol, boolean[][] rows,
            boolean[][] cols, boolean[][] grids) {
        int row, col, grid;
        if (prevCol == -1) {
            row = 0;
            col = 0;
        } else if (prevCol < BOARD_SIZE - 1) {
            row = prevRow;
            col = prevCol + 1;
        } else {
            row = prevRow + 1;
            col = 0;
        }
        grid = GRID_SIZE * (row / GRID_SIZE) + col / GRID_SIZE;

        if (row == BOARD_SIZE) {
            // we have successfully filled all cells
            return true;
        } else if (board[row][col] != EMPTY) {
            // this cell is already filled initially. move on.
            return fill(board, row, col, rows, cols, grids);
        }

        for (char ch = '1'; ch <= '9'; ch++) {
            int k = ch - '1';
            if (!rows[row][k] && !cols[col][k] && !grids[grid][k]) {
                // ch is not already present in the current row, col, and grid. let's try it
                rows[row][k] = true;
                cols[col][k] = true;
                grids[grid][k] = true;
                board[row][col] = ch;
                if (fill(board, row, col, rows, cols, grids)) {
                    return true;
                }
                // no luck. we need to revert and make another try
                board[row][col] = EMPTY;
                rows[row][k] = false;
                cols[col][k] = false;
                grids[grid][k] = false;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        char[][] board = new char[][] { { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
        solveSudoku(board);
    }

}
