package problems.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/the-skyline-problem/
 */
public class SkylineProblem {

    private static class Point implements Comparable<Point> {
        final int x;
        final int h;
        final boolean start;

        Point(int x, int h, boolean start) {
            this.x = x;
            this.h = h;
            this.start = start;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", h=" + h +
                    ", start=" + start +
                    '}';
        }

        @Override
        public int compareTo(Point other) {
            int cx = Integer.compare(this.x, other.x);
            int ch = Integer.compare(this.h, other.h);

            // compare by x
            if (cx != 0) {
                return cx;
            }

            // If both are on the same x, do the following

            if (this.start && other.start) {
                // If both points are start, reverse order by height
                return -ch;
            } else if (!this.start && !other.start) {
                // If both points are end, order by height
                return ch;
            } else {
                // If one is start and the other one is end,
                // start should appear before end
                return -Boolean.compare(this.start, other.start);
            }
        }
    }

    public static List<List<Integer>> getSkyline(int[][] buildings) {
        if (buildings == null || buildings.length == 0) {
            return Collections.emptyList();
        }

        List<List<Integer>> skyline = new ArrayList<>();

        List<Point> points = new ArrayList<>(buildings.length * 2);
        for (int[] building : buildings) {
            points.add(new Point(building[0], building[2], true));
            points.add(new Point(building[1], building[2], false));
        }

        Collections.sort(points);

        // PriorityQueue does not support O(lgn) removal...
        // height -> occurrence

        TreeMap<Integer, Integer> queue = new TreeMap<>();
        queue.put(0, 1);
        int prevMaxH = 0;
        for (Point p : points) {
            if (p.start) {
                queue.put(p.h, 1 + queue.getOrDefault(p.h, 0));
            } else {
                int count = queue.getOrDefault(p.h, 0);
                if (count > 1) {
                    queue.put(p.h, count - 1);
                } else if (count == 1) {
                    queue.remove(p.h);
                }
            }

            int maxH = queue.lastKey();
            if (maxH != prevMaxH) {
                skyline.add(Arrays.asList(p.x, maxH));
                prevMaxH = maxH;
            }
        }

        return skyline;
    }


    public static void main(String[] args) {
//        int[][] buildings = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
//        int[][] buildings = {{1, 3, 3}, {2, 4, 4}, {5, 8, 2}, {6, 7, 4}, {8, 9, 4}};4
        int[][] buildings = {{0, 2, 3}, {2, 5, 3}};
        List<List<Integer>> skyline = getSkyline(buildings);
        for (List<Integer> b : skyline) {
            System.out.println(b);
        }
    }

}
