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

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> insertedIntervals = new ArrayList<>(intervals.length);
        int i = 0;
        while (i < intervals.length && intervals[i][0] <= newInterval[0]) {
            insertedIntervals.add(intervals[i++]);
        }

        insertedIntervals.add(newInterval);

        while (i < intervals.length) {
            insertedIntervals.add(intervals[i++]);
        }

        LinkedList<int[]> mergedIntervals = new LinkedList<>();

        for (int[] interval : insertedIntervals) {
            if (mergedIntervals.isEmpty() || mergedIntervals.getLast()[1] < interval[0]) {
                mergedIntervals.add(interval);
                continue;
            }

            int[] prev = mergedIntervals.removeLast();
            mergedIntervals.add(new int[]{prev[0], Math.max(prev[1], interval[1])});
        }


        return mergedIntervals.toArray(new int[0][0]);
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
