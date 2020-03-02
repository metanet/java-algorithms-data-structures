package problems.leetcode;

/**
 * https://leetcode.com/problems/search-a-2d-matrix-ii/
 */
public class SearchIn2DMatrixII {

    public static void main(String[] args) {
        int[][] matrix = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        int target = 16;
        System.out.println(searchMatrix(matrix, target));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        int row = matrix.length - 1, col = 0;

        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] > target) {
                // on this row, cols on the right are definitely greater than target
                row--;
            } else if (matrix[row][col] < target) {
                // on this col, rows above are definitely smaller than target
                col++;
            } else {
                return true;
            }
        }

        return false;
    }

}
