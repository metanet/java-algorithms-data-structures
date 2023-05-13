package problems.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/insert-interval/
 */
public class InsertInterval {

    public static void main(String[] args) {
        int[][] intervals = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval = {4, 8};
//        int[][] intervals = {{1, 3}, {6, 9}};
//        int[] newInterval = {1, 5};


        int[][] inserted = insert(intervals, newInterval);

    }

    // runtime: O(N)
    // space: O(N)
    public static int[][] insert(int[][] intervals1, int[] newInterval) {
        if (intervals1 == null || intervals1.length < 1) {
            return new int[][] {newInterval};
        }

        int[][] intervals2 = new int[][]{newInterval};
        int i = 0, j = 0, n = intervals1.length, m = 1;
        int[] interval = intervals1[i][0] > intervals2[j][0] ? intervals2[j++] : intervals1[i++];
        List<int[]> result = new ArrayList<>();

        while (i < n || j < m) {
            int[] next;
            if (i < n && j < m) {
                next = intervals1[i][0] > intervals2[j][0] ? intervals2[j++] : intervals1[i++];   
            } else if (i < n) {
                next = intervals1[i++];
            } else {
                next = intervals2[j++];
            }
            if (next[0] <= interval[1]) {
                interval[1] = Math.max(interval[1], next[1]);
            } else {
                result.add(interval);
                interval = next;
            }
        }

        result.add(interval);

        return result.toArray(new int[0][0]);
    }

    /*

    [1,2], [3,5], [6,7], [8,10], [12,16] new interval: [4, 8]

    [1,2], [3,5], [4,8], [6,7] [8,10], [12, 16]

    [1,2], [3,5]
    [1,2], [3,8]
    [1,2], [3,8], [6,7]
    [1,2], [3,8]
    [1,2], [3,8], [8, 10]
    [1,2], [3,10]
    [1,2], [3,10], [12, 16]


     */

}
