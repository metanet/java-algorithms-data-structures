package problems.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/course-schedule/
 */
public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List[] preLists = buildAdjList(numCourses, prerequisites);

        boolean[] addedToQueue = new boolean[numCourses];
        for (int course = 0; course < numCourses; course++) {
            if (addedToQueue[course]) {
                continue;
            }

            Queue<Integer> coursePrerequisites = new ArrayDeque<>();
            boolean[] path = new boolean[numCourses];
            coursePrerequisites.add(course);
            addedToQueue[course] = true;
            while (coursePrerequisites.size() > 0) {
                int crs = coursePrerequisites.poll();
                if (path[crs]) {
                    return false;
                }

                path[crs] = true;

                if (preLists[crs] != null) {
                    for (int prerequisite : (List<Integer>) preLists[crs]) {
                        if (!addedToQueue[prerequisite]) {
                            coursePrerequisites.add(prerequisite);
                            addedToQueue[prerequisite] = true;
                        } else if (path[prerequisite]) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
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

    public static void main(String[] args) {
        System.out.println(new CourseSchedule().canFinish(2, new int[][]{{0, 1}}));
    }

}
