package problems.leetcode;

/**
 * https://leetcode.com/problems/word-search/
 */
public class WordSearch {

    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCB";
        System.out.println(exist(board, word));
    }

    public static boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (exist(board, i, j, word, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean exist(char[][] board, int i, int j, String word, int k) {
        if (k == word.length()) {
            return true;
        } else if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(k)) {
            return false;
        }

        board[i][j] = '.';

        boolean success =
                exist(board, i + 1, j, word, k + 1) || exist(board, i - 1, j, word, k + 1) || exist(board, i, j + 1, word, k + 1)
                        || exist(board, i, j - 1, word, k + 1);

        if (!success) {
            board[i][j] = word.charAt(k);
        }

        return success;
    }

}
