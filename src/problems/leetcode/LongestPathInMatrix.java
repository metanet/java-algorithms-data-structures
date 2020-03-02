package problems.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
 */
public class LongestPathInMatrix {

    public static void main(String[] args) {
        //        int[][] matrix = new int[][]{{9, 9, 4}, {6, 6, 8}, {2, 2, 1}};
        int[][] matrix = new int[][]{{3, 4, 5}, {3, 2, 6}, {2, 2, 1}};
        int longestDistance = new LongestPathInMatrix().longestIncreasingPath(matrix);
        System.out.println(longestDistance);
    }

    private int[][] matrix;
    private int N, M, longestDistance;
    private int[][] knownLongestDistances;

    public int longestIncreasingPath(int[][] matrix) {
        this.matrix = matrix;
        this.N = matrix.length;
        if (N == 0) {
            return 0;
        }
        this.M = matrix[0].length;
        if (M == 0) {
            return 0;
        }

        knownLongestDistances = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(knownLongestDistances[i], -1);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                longestDistance = Math.max(longestDistance, travel(i, j));
            }
        }

        return longestDistance;
    }

    private int travel(int i, int j) {
        if (knownLongestDistances[i][j] != -1) {
            return knownLongestDistances[i][j];
        }

        int distance = 1;
        distance = tryTravel(i, j, i + 1, j, distance);
        distance = tryTravel(i, j, i - 1, j, distance);
        distance = tryTravel(i, j, i, j + 1, distance);
        distance = tryTravel(i, j, i, j - 1, distance);

        knownLongestDistances[i][j] = distance;

        return distance;
    }

    private int tryTravel(int i1, int j1, int i2, int j2, int distance) {
        return canMove(i1, j1, i2, j2) ? Math.max(distance, 1 + travel(i2, j2)) : distance;
    }

    private boolean canMove(int i1, int j1, int i2, int j2) {
        if (i2 < 0 || i2 >= N || j2 < 0 || j2 >= M) {
            return false;
        }

        return matrix[i1][j1] < matrix[i2][j2];
    }

}
