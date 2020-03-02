package problems.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/k-closest-points-to-origin/
 */
public class KClosestPointsToTheOrigin {

    public static int[][] kClosest(int[][] coordinates, int k) {
        if (coordinates.length <= k) {
            return coordinates;
        }

        k--;
        int l = 0, r = coordinates.length - 1;
        while (true) {
            int m = partition(coordinates, l, r);
            if (m > k) {
                r = m - 1;
            } else if (m < k) {
                l = m + 1;
            } else {
                break;
            }
        }

        return Arrays.copyOf(coordinates, k + 1);
    }

    private static int partition(int[][] coordinates, int l, int r) {
        int pivotDistance = distance(coordinates[r]);
        int i = l;
        for (int j = l; j < r; j++) {
            if (distance(coordinates[j]) < pivotDistance) {
                swap(coordinates, i++, j);
            }
        }

        swap(coordinates, i, r);
        return i;
    }

    private static void swap(int[][] coordinates, int i, int j) {
        int x = coordinates[i][0], y = coordinates[i][1];
        coordinates[i][0] = coordinates[j][0];
        coordinates[i][1] = coordinates[j][1];
        coordinates[j][0] = x;
        coordinates[j][1] = y;
    }

    private static int distance(int[] coordinate) {
        return coordinate[0] * coordinate[0] + coordinate[1] * coordinate[1];
    }

    public static void main(String[] args) {
        int[][] points = {{1, 3}, {-2, 2}, {1, 1}};

        int[][] kClosest = kClosest(points, 2);

        for (int[] c : kClosest) {
            System.out.println(Arrays.toString(c));
        }
    }

}
