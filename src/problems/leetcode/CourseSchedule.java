package problems.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/course-schedule/
 */
public class CourseSchedule {

    public boolean canFinish2(int numCourses, int[][] prerequisites) {
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
        // System.out.println(new CourseSchedule().canFinish2(2, new int[][] { { 0, 1 }
        // }));
        // System.out.println(canFinish(5, new int[][] { { 1, 4 }, { 2, 4 }, { 3, 1 }, {
        // 3, 2 } }));
        System.out.println(canFinish(100,
                new int[][] { { 1, 0 }, { 2, 0 }, { 2, 1 }, { 3, 1 }, { 3, 2 }, { 4, 2 }, { 4, 3 }, { 5, 3 },
                        { 5, 4 }, { 6, 4 }, { 6, 5 }, { 7, 5 }, { 7, 6 }, { 8, 6 }, { 8, 7 }, { 9, 7 }, { 9, 8 },
                        { 10, 8 }, { 10, 9 }, { 11, 9 }, { 11, 10 }, { 12, 10 }, { 12, 11 }, { 13, 11 }, { 13, 12 },
                        { 14, 12 }, { 14, 13 }, { 15, 13 }, { 15, 14 }, { 16, 14 }, { 16, 15 }, { 17, 15 }, { 17, 16 },
                        { 18, 16 }, { 18, 17 }, { 19, 17 }, { 19, 18 }, { 20, 18 }, { 20, 19 }, { 21, 19 }, { 21, 20 },
                        { 22, 20 }, { 22, 21 }, { 23, 21 }, { 23, 22 }, { 24, 22 }, { 24, 23 }, { 25, 23 }, { 25, 24 },
                        { 26, 24 }, { 26, 25 }, { 27, 25 }, { 27, 26 }, { 28, 26 }, { 28, 27 }, { 29, 27 }, { 29, 28 },
                        { 30, 28 }, { 30, 29 }, { 31, 29 }, { 31, 30 }, { 32, 30 }, { 32, 31 }, { 33, 31 }, { 33, 32 },
                        { 34, 32 }, { 34, 33 }, { 35, 33 }, { 35, 34 }, { 36, 34 }, { 36, 35 }, { 37, 35 }, { 37, 36 },
                        { 38, 36 }, { 38, 37 }, { 39, 37 }, { 39, 38 }, { 40, 38 }, { 40, 39 }, { 41, 39 }, { 41, 40 },
                        { 42, 40 }, { 42, 41 }, { 43, 41 }, { 43, 42 }, { 44, 42 }, { 44, 43 }, { 45, 43 }, { 45, 44 },
                        { 46, 44 }, { 46, 45 }, { 47, 45 }, { 47, 46 }, { 48, 46 }, { 48, 47 }, { 49, 47 }, { 49, 48 },
                        { 50, 48 }, { 50, 49 }, { 51, 49 }, { 51, 50 }, { 52, 50 }, { 52, 51 }, { 53, 51 }, { 53, 52 },
                        { 54, 52 }, { 54, 53 }, { 55, 53 }, { 55, 54 }, { 56, 54 }, { 56, 55 }, { 57, 55 }, { 57, 56 },
                        { 58, 56 }, { 58, 57 }, { 59, 57 }, { 59, 58 }, { 60, 58 }, { 60, 59 }, { 61, 59 }, { 61, 60 },
                        { 62, 60 }, { 62, 61 }, { 63, 61 }, { 63, 62 }, { 64, 62 }, { 64, 63 }, { 65, 63 }, { 65, 64 },
                        { 66, 64 }, { 66, 65 }, { 67, 65 }, { 67, 66 }, { 68, 66 }, { 68, 67 }, { 69, 67 }, { 69, 68 },
                        { 70, 68 }, { 70, 69 }, { 71, 69 }, { 71, 70 }, { 72, 70 }, { 72, 71 }, { 73, 71 }, { 73, 72 },
                        { 74, 72 }, { 74, 73 }, { 75, 73 }, { 75, 74 }, { 76, 74 }, { 76, 75 }, { 77, 75 }, { 77, 76 },
                        { 78, 76 }, { 78, 77 }, { 79, 77 }, { 79, 78 }, { 80, 78 }, { 80, 79 }, { 81, 79 }, { 81, 80 },
                        { 82, 80 }, { 82, 81 }, { 83, 81 }, { 83, 82 }, { 84, 82 }, { 84, 83 }, { 85, 83 }, { 85, 84 },
                        { 86, 84 }, { 86, 85 }, { 87, 85 }, { 87, 86 }, { 88, 86 }, { 88, 87 }, { 89, 87 }, { 89, 88 },
                        { 90, 88 }, { 90, 89 }, { 91, 89 }, { 91, 90 }, { 92, 90 }, { 92, 91 }, { 93, 91 }, { 93, 92 },
                        { 94, 92 }, { 94, 93 }, { 95, 93 }, { 95, 94 }, { 96, 94 }, { 96, 95 }, { 97, 95 }, { 97, 96 },
                        { 98, 96 }, { 98, 97 }, { 99, 97 } }));

    }

    private static class Preq {
        int course;
        int preq;

        Preq(int course, int preq) {
            this.course = course;
            this.preq = preq;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + course;
            result = prime * result + preq;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Preq other = (Preq) obj;
            if (course != other.course)
                return false;
            if (preq != other.preq)
                return false;
            return true;
        }
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        // course -> preq course
        Map<Integer, List<Integer>> edges = new HashMap<>();
        for (int[] p : prerequisites) {
            edges.computeIfAbsent(p[0], (k) -> new ArrayList<>()).add(p[1]);
        }

        // System.out.println(edges);

        for (int course = 0; course < numCourses; course++) {
            if (!dfs(edges, course, new HashSet<>(), new HashSet<>())) {
                return false;
            }
        }

        return true;
    }

    private static boolean dfs(Map<Integer, List<Integer>> edges, int course, Set<Integer> seen, Set<Preq> successPreqs) {
        if (!seen.add(course)) {
            return false;
        }

        List<Integer> preqs = edges.get(course);
        if (preqs == null) {
            return true;
        }

        boolean success = true;
        for (int preq : preqs) {
            Preq p = new Preq(course, preq);
            if (successPreqs.contains(p)) {
                continue;
            }
            success &= dfs(edges, preq, seen, successPreqs);
            if (success) {
                seen.remove(preq);
                successPreqs.add(p);
            } else {
                break;
            }
        }

        return success;
    }

}
