package problems.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/interval-list-intersections/
 */
public class IntervalListIntersections {

    public static void main(String[] args) {
//        int[][] A = {{0, 2}, {5, 10}, {13, 23}, {24, 25}};
//        int[][] B = {{1, 5}, {8, 12}, {15, 24}, {25, 26}};

        int[][] A = {{17, 20}};
        int[][] B = {{2, 3}, {6, 8}, {12, 14}, {19, 20}};

        int[][] intersections = intervalIntersection(A, B);
        for (int[] intersection : intersections) {
            System.out.println(Arrays.toString(intersection));
        }
    }

    public static int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> intersections = new ArrayList<>();

        int i = 0, j = 0;
        while (i < A.length && j < B.length) {
            if (A[i][1] < B[j][0]) {
                // if B's start is greater than A's end, proceed on A
                i++;
                continue;
            } else if (A[i][0] > B[j][1]) {
                // if A's start is greater than B's end, proceed on B
                j++;
                continue;
            }

            int start = Math.max(A[i][0], B[j][0]), end = Math.min(A[i][1], B[j][1]);
            intersections.add(new int[]{start, end});

            if (A[i][1] < B[j][1]) {
                i++;
            } else {
                j++;
            }
        }

        return intersections.toArray(new int[0][0]);
    }
}
