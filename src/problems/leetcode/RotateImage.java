package problems.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/rotate-image/
 */
public class RotateImage {

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}, {21, 22, 23, 24, 25}};
        rotate(matrix);
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }


    public static void rotate(int[][] matrix) {
        for (int i = 0, n = matrix.length, k = n - 1; i < (n + 1) / 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                int i1 = k - j, j1 = i;
                int i2 = k - i, j2 = k - j;
                int i3 = j, j3 = k - i;
                int i4 = i, j4 = j;
                int temp = matrix[i1][j1];
                matrix[i1][j1] = matrix[i2][j2];
                matrix[i2][j2] = matrix[i3][j3];
                matrix[i3][j3] = matrix[i4][j4];
                matrix[i4][j4] = temp;
            }
        }
    }

    /*

     1, 2, 3, 4, 5
     6, 7, 8, 9,10
    11,12,13,14,15
    16,17,18,19,20
    21,22,23,24,25




    [1,2,3],
    [4,5,6],
    [7,8,9]

    [7,4,1],
    [8,5,2],
    [9,6,3]

     1,6,3
     4,5,2
     7,8,9

     9,6,1
     4,5,2
     7,8,3

     9,8,1
     4,5,2
     7,6,3

     9,4,1
     8,5,2
     7,6,3

     7,4,1
     8,5,2
     9,6,3





    [ 5, 1, 9,11],
    [ 2, 4, 8,10],
    [13, 3, 6, 7],
    [15,14,12,16]

    [15,13, 2, 5],
    [14, 3, 4, 1],
    [12, 6, 8, 9],
    [16, 7,10,11]


     15, 1, 9, 5
     2 , 4, 8,10
     13, 3, 6, 7
     16,14,12,11

     15,13, 9, 5
     2 , 4, 8, 1
     12, 3, 6, 7
     16,14,10,11

     15,13, 2, 5
     14, 4, 8, 1
     12, 3, 6, 9
     16, 7,10,11








     */

}
