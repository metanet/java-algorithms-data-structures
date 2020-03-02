package problems.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/course-schedule-ii/
 */
public class CourseScheduleII {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List[] preLists = buildAdjList(numCourses, prerequisites);

        boolean[] done = new boolean[numCourses];
        List<Integer> completedCourses = new ArrayList<>(numCourses);
        for (int course = 0; course < numCourses; course++) {
            if (!done[course]) {
                try {
                    DFS(course, preLists, new HashSet<>(), done, completedCourses);
                } catch (IllegalArgumentException e) {
                    return new int[0];
                }
            }
        }

        int[] result = new int[completedCourses.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = completedCourses.get(i);
        }

        return result;
    }

    private List[] buildAdjList(int numCourses, int[][] prerequisites) {
        List[] preLists = new List[numCourses];
        for (int[] p : prerequisites) {
            int course = p[0];
            int pre = p[1];
            List<Integer> preList = preLists[course];
            if (preList == null) {
                preLists[course] = preList = new ArrayList<>();
            }

            preList.add(pre);
        }
        return preLists;
    }

    private void DFS(int course, List[] preLists, Set<Integer> visited, boolean[] done, List<Integer> completedCourses) {
        visited.add(course);
        done[course] = true;
        List<Integer> preCourses = preLists[course];
        if (preCourses != null) {
            for (int preCourse : preCourses) {
                if (visited.contains(preCourse)) {
                    throw new IllegalArgumentException();
                }
                if (!done[preCourse]) {
                    DFS(preCourse, preLists, visited, done, completedCourses);
                }
            }
        }

        visited.remove(course);

        completedCourses.add(course);
    }

    public static void main(String[] args) {
        int[] order = new CourseScheduleII().findOrder(5, new int[][]{{0, 1}, {1, 2}, {1, 3}, {3, 4}, {4, 3}});
//        int[] order = new CourseScheduleII().findOrder(3, new int[][] {{0, 1}, {0, 2}, {1, 2}});
        System.out.println(Arrays.toString(order));
    }

}
