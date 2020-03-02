package problems.leetcode;

/**
 * https://leetcode.com/problems/maximal-square/
 */
public class MaximalSquare {

    public static void main(String[] args) {
        char[][] matrix = {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};

        int maxArea = new MaximalSquare().maximalSquare(matrix);
        System.out.println(maxArea);
        System.out.println(maximalSquareDP(matrix));
    }

       /*
        We track the max side edge of a square with ones with the variable res.
        We iterate through the 2D array and at each element, we first check if
        the element is a '1'. If this is the case, we check for the min square
        of ones possible at [i][j-1], [i-1][j]and[i-1][j-1] indices. We increment
        dp[i][j] by the min square possible at mentioned neighbor indices.
        Trivially, at j = 0 and i = 0 we just make check for '1' and set dp[i][j]
        to 1 if this condition is true.
         */

    public static int maximalSquareDP(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int m = matrix.length, n = matrix[0].length, maxLen = 0;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = 1;
                    if (i > 0 && j > 0) {
                        dp[i][j] += Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                    }

                    maxLen = Math.max(dp[i][j], maxLen);
                }
            }
        }

        return maxLen * maxLen;
    }

    private char[][] matrix;
    private int m, n;
    private int maximumSquareArea;

    public int maximalSquare(char[][] matrix) {
        this.matrix = matrix;
        this.m = matrix.length;
        if (m == 0) {
            return 0;
        }

        this.n = matrix[0].length;
        if (n == 0) {
            return 0;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    int area = getSquareArea(i, j);
//                    System.out.println("i: " + i + ", j: " + j + ", area: " + area);
                    maximumSquareArea = Math.max(maximumSquareArea, area);
                }
            }
        }

        return maximumSquareArea;
    }

    private int getSquareArea(int originRow, int originCol) {
        int len = 1;
        while (isSquare(originRow, originCol, originRow + len, originCol + len)) {
            len++;
        }

        return len * len;
    }

    private boolean isSquare(int originRow, int originCol, int row, int col) {
        if (row >= m || col >= n) {
            return false;
        }

        for (int i = originCol; i <= col; i++) {
            if (matrix[row][i] == '0') {
                return false;
            }
        }

        for (int i = originRow; i <= row; i++) {
            if (matrix[i][col] == '0') {
                return false;
            }
        }

        return true;
    }

}
