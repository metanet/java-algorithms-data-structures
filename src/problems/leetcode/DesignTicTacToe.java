package problems.leetcode;

/**
 * https://leetcode.com/problems/design-tic-tac-toe/
 */
public class DesignTicTacToe {

    private static class TicTacToe {

        int n;
        int[] rows, cols;
        int diag1, diag2;

        public TicTacToe(int n) {
            this.n = n;
            this.rows = new int[n];
            this.cols = new int[n];
        }

        /**
         * Player {player} makes a move at ({row}, {col}).
         *
         * @param row    The row of the board.
         * @param col    The column of the board.
         * @param player The player, can be either 1 or 2.
         * @return The current winning condition, can be either:
         * 0: No one wins.
         * 1: Player 1 wins.
         * 2: Player 2 wins.
         */
        public int move(int row, int col, int player) {
            if (rows[row] != n + 1) {
                if (rows[row] >= 0 && player == 1) {
                    if (++rows[row] == n) {
                        return player;
                    }
                } else if (rows[row] <= 0 && player == 2) {
                    if (--rows[row] == -n) {
                        return player;
                    }
                } else {
                    rows[row] = n + 1;
                }
            }

            if (cols[col] != n + 1) {
                if (cols[col] >= 0 && player == 1) {
                    if (++cols[col] == n) {
                        return player;
                    }
                } else if (cols[col] <= 0 && player == 2) {
                    if (--cols[col] == -n) {
                        return player;
                    }
                } else {
                    cols[col] = n + 1;
                }
            }

            if (row == col) {
                if (diag1 >= 0 && player == 1) {
                    if (++diag1 == n) {
                        return player;
                    }
                } else if (diag1 <= 0 && player == 2) {
                    if (--diag1 == -n) {
                        return player;
                    }
                } else {
                    diag1 = n + 1;
                }
            }

            if (row + col == n - 1) {
                if (diag2 >= 0 && player == 1) {
                    if (++diag2 == n) {
                        return player;
                    }
                } else if (diag2 <= 0 && player == 2) {
                    if (--diag2 == -n) {
                        return player;
                    }
                } else {
                    diag2 = n + 1;
                }
            }

            return 0;
        }
    }

    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe(3);

        System.out.println(ticTacToe.move(1, 1, 2));
        System.out.println(ticTacToe.move(0, 2, 1));
        System.out.println(ticTacToe.move(2, 0, 2));
    }


}
