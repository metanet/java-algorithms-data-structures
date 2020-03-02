package problems.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/valid-sudoku/
 */
public class ValidSudoku {

    static final int BOARD_SIZE = 9;
    static final int BOX_SIZE = BOARD_SIZE / 3;

    public static boolean isValidSudoku(char[][] board) {
        if (board == null || board.length != BOARD_SIZE || board[0].length != BOARD_SIZE) {
            throw new IllegalArgumentException();
        }

        boolean[] nums = new boolean[10];
        for (int row = 0; row < BOARD_SIZE; row++) {
            Arrays.fill(nums, false);
            if (!checkRow(board, row, 0, BOARD_SIZE, nums)) {
                return false;
            }
        }

        for (int col = 0; col < BOARD_SIZE; col++) {
            Arrays.fill(nums, false);
            if (!checkCol(board, 0, col, BOARD_SIZE, nums)) {
                return false;
            }
        }

        for (int row = 0; row < BOARD_SIZE; row += BOX_SIZE) {
            for (int col = 0; col < BOARD_SIZE; col += BOX_SIZE) {
                Arrays.fill(nums, false);
                for (int i = row; i < row + BOX_SIZE; i++) {
                    if (!checkRow(board, i, col, BOX_SIZE, nums)) {
                        return false;
                    }
                }
            }
        }

        return true;

    }

    private static boolean checkRow(char[][] board, int row, int col, int size, boolean[] nums) {
        for (int i = col; i < col + size; i++) {
            if (!checkCell(board[row][i], nums)) {
                return false;
            }
        }

        return true;
    }

    private static boolean checkCol(char[][] board, int row, int col, int size, boolean[] nums) {
        for (int i = row; i < row + size; i++) {
            if (!checkCell(board[i][col], nums)) {
                return false;
            }
        }

        return true;
    }

    private static boolean checkCell(char c, boolean[] nums) {
        if (c == '.') {
            return true;
        }
        int num = c - '0';
        if (nums[num]) {
            return false;
        }

        nums[num] = true;
        return true;
    }

    public static void main(String[] args) {
        char[][] board = {{'.', '.', '.', '.', '.', '.', '5', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'9', '3', '.', '.', '2', '.', '4', '.', '.'},
                {'.', '.', '7', '.', '.', '.', '3', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '3', '4', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '3', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '5', '2', '.', '.'}};

        System.out.println(isValidSudoku(board));
    }

}
