package problems.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.com/problems/merge-intervals/
 */
public class MergeIntervals {

    public static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][0];
        }

        Comparator<Object> c = Comparator.comparingInt(interval -> ((int[]) interval)[0])
                .thenComparingInt(interval -> ((int[]) interval)[1]);
        Arrays.sort(intervals, c);

        List<int[]> merged = new ArrayList<>();
        int start = intervals[0][0], end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (interval[0] > end) {
                merged.add(new int[]{start, end});
                start = interval[0];
                end = interval[1];
            } else if (interval[1] > end) {
                end = interval[1];
            }
        }

        merged.add(new int[]{start, end});

        return merged.toArray(new int[0][0]);
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] overlaps = merge(intervals);
        for (int i = 0; i < overlaps.length; i++) {
            System.out.println(Arrays.toString(overlaps[i]));
        }
    }

}
