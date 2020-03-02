package problems.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/number-of-distinct-islands/
 */
public class NumberOfDistinctIslands {

    public static void main(String[] arg) {
        int[][] grid = {{1, 1, 0, 1, 1}, {1, 0, 0, 0, 0}, {0, 0, 0, 0, 1}, {1, 1, 0, 1, 1}};
        System.out.println(numDistinctIslands(grid));
    }

    public static int numDistinctIslands(int[][] grid) {
        Set<List<Distance>> islands = new HashSet<>();

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    List<Distance> island = new ArrayList<>();
                    mark(grid, row, col, row, col, island);
                    Collections.sort(island);
//                    System.out.println(island);
                    islands.add(island);
                }
            }
        }

        return islands.size();
    }

    private static void mark(int[][] grid, int originRow, int originCol, int row, int col, List<Distance> island) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[row].length || grid[row][col] != 1) {
            return;
        }

        island.add(new Distance(row - originRow, col - originCol));
        grid[row][col] = -1;

        mark(grid, originRow, originCol, row + 1, col, island);
        mark(grid, originRow, originCol, row - 1, col, island);
        mark(grid, originRow, originCol, row, col + 1, island);
        mark(grid, originRow, originCol, row, col - 1, island);
    }

    private static class Distance implements Comparable<Distance> {
        final int x;
        final int y;

        Distance(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Distance distance = (Distance) o;

            if (x != distance.x) return false;
            return y == distance.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }

        @Override
        public int compareTo(Distance other) {
            int c = Integer.compare(this.x, other.x);
            return c != 0 ? c : Integer.compare(this.y, other.y);
        }

        @Override
        public String toString() {
            return "D{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

}
