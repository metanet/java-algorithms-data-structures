package problems.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/minimum-time-difference/
 */
public class MinimumTimeDifference {

    public int findMinDifferenceON(List<String> timePoints) {
        boolean[] minutes = new boolean[24 * 60];

        for (String str : timePoints) {
            String[] tokens = str.split(":");
            int hour = Integer.parseInt(tokens[0]);
            int min = Integer.parseInt(tokens[1]);
            int time = hour * 60 + min;
            if (minutes[time]) {
                // the same time is seen twice
                return 0;
            }

            minutes[time] = true;
        }

        int minTimeDiff = Integer.MAX_VALUE, lastIdx = -1, firstIdx = -1;
        for (int i = 0; i < minutes.length; ++i) {
            if (!minutes[i]) {
                continue;
            }

            if (firstIdx == -1) {
                firstIdx = i;
            }

            if (lastIdx != -1) {
                minTimeDiff = Math.min(i - lastIdx, minTimeDiff);
            }

            lastIdx = i;
        }

        minTimeDiff = Math.min(minutes.length - 1 - lastIdx + firstIdx + 1, minTimeDiff);

        return minTimeDiff;
    }

    public static int findMinDifference(List<String> timePoints) {
        if (timePoints == null || timePoints.size() < 2) {
            return 0;
        }

        Collections.sort(timePoints);
        int[][] arr = parse(timePoints);

        int minDiff = minDiff(arr[timePoints.size() - 1], arr[0]);
        for (int i = 0; i < timePoints.size() - 1; i++) {
            int diff = minDiff(arr[i], arr[i + 1]);
            if (diff < minDiff) {
                minDiff = diff;
            }
            if (minDiff == 0) {
                break;
            }
        }

        return minDiff;
    }

    private static int minDiff(int[] time1, int[] time2) {
        int t2 = 60 * time2[0] + time2[1];
        int t1 = 60 * time1[0] + time1[1];
        if (t2 < t1) {
            return Math.min(t1 - t2, 60 * 24 + t2 - t1);
        } else {
            return t2 - t1;
        }
    }

    private static int[][] parse(List<String> timePoints) {
        int[][] arr = new int[timePoints.size()][2];
        for (int i = 0; i < timePoints.size(); i++) {
            String[] tokens = timePoints.get(i).split(":");
            arr[i][0] = Integer.parseInt(tokens[0]);
            arr[i][1] = Integer.parseInt(tokens[1]);
        }

        return arr;
    }

    public static void main(String[] args) {
        List<String> timePoints = Arrays.asList("23:59", "00:00");

        System.out.println(findMinDifference(timePoints));
    }


}
