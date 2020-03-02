package problems.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/meeting-rooms-ii/
 */
public class MeetingRoomsII {

    public static void main(String[] args) {
//        int[][] intervals = {{0, 30}, {5, 10}, {15, 20}};
//        int[][] intervals = {{5, 8}, {6, 8}};
        int[][] intervals = {{1, 5}, {8, 9}, {8, 9}};
        int minRooms = minMeetingRooms(intervals);
        System.out.println(minRooms);
    }

    public static int minMeetingRooms(int[][] intervals) {
        if (intervals == null) {
            return 0;
        } else if ( intervals.length < 2) {
            return intervals.length;
        }

        // first compare by start time, then compare by end time
        Arrays.sort(intervals, Comparator.comparingInt((int[] meeting) -> meeting[0]).thenComparingInt(meeting -> meeting[1]));

        PriorityQueue<Integer> rooms = new PriorityQueue<>();
        rooms.add(intervals[0][1]);

        // Iterate over remaining meetings
        for (int i = 1; i < intervals.length; i++) {
            // If the room with the earliest end time is free when this meeting starts,
            // assign that room to this meeting.
            if (intervals[i][0] >= rooms.peek()) {
                rooms.poll();
            }

            rooms.add(intervals[i][1]);
        }

        return rooms.size();
    }


}
