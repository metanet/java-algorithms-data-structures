package problems.leetcode;

/**
 * https://leetcode.com/problems/unique-paths/
 */
public class UniquePaths {

    public static void main(String[] args) {
        int m = 2;
        int n = 1;
        System.out.println(uniquePaths(m, n));
    }

    public static int uniquePaths(int m, int n) {
        if (m == 1 && n == 1) {
            return 1;
        }

        int[][] counts = new int[m][n];

        for (int i = 0; i < m - 1; i++) {
            counts[i][n - 1] = 1;
        }

        for (int j = 0; j < n - 1; j++) {
            counts[m - 1][j] = 1;
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                counts[i][j] = counts[i + 1][j] + counts[i][j + 1];
            }
        }

        return counts[0][0];
    }


    /*

    x . .
    . . .
    . . y

    r r d d
    r d r d
    r d d r
    d d r d

     */

}
