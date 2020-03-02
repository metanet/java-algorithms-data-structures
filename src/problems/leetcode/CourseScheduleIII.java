package problems.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/course-schedule-iii/
 */
public class CourseScheduleIII {

    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, Comparator.comparingInt(course -> course[1]));
        PriorityQueue<Integer> taken = new PriorityQueue<>(Comparator.reverseOrder());
        int time = 0;
        for (int[] course : courses) {
            int duration = course[0], deadline = course[1];
            if (time + duration <= deadline) {
                taken.offer(duration);
                time += duration;
            } else if (!taken.isEmpty() && duration < taken.peek() && time + duration - taken.peek() <= deadline) {
                time += duration - taken.poll();
                taken.offer(duration);
            }
        }

        return taken.size();
    }

    public static void main(String[] args) {
        int[][] courses = {{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}};
        //        int[][] courses = {{5, 5}, {4, 6}, {2, 6}};
        System.out.println(new CourseScheduleIII().scheduleCourse(courses));
    }

}
