package problems.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/shortest-distance-from-all-buildings/
 */
public class ShortestDistanceFromAllBuildings {
    public static void main(String[] args) {
        //        int[][] grid = new int[][]{{1, 0, 2, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}};
        //        int[][] grid = new int[][]{{0, 2, 1}, {1, 0, 2}, {0, 1, 0}};
        //        int[][] grid = new int[][]{{1, 1, 1, 1, 1, 0}, {0, 0, 0, 0, 0, 1}, {0, 1, 1, 0, 0, 1}, {1, 0, 0, 1, 0, 1}, {1, 0, 1, 0, 0, 1}, {1, 0, 0, 0, 0, 1}, {0, 1, 1, 1, 1, 0}};
        int[][] grid = new int[][]{{0, 1, 2, 0, 2, 0, 2, 1, 2, 0, 0, 2, 2, 2, 2, 0, 2, 0, 2, 0, 2, 2, 2, 2, 0}, {2, 0, 0, 0, 1, 0, 0, 0, 2, 2, 0, 0, 0, 2, 2, 0, 0, 2, 2, 0, 2, 0, 0, 0, 2}, {0, 2, 0, 2, 2, 0, 0, 0, 0, 2, 2, 0, 2, 0, 0, 2, 0, 0, 0, 2, 0, 2, 0, 0, 2}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 0, 0, 2, 1, 1, 0, 0, 0, 2, 0, 2}, {2, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 1, 2, 2, 0, 2, 2, 2, 0, 0, 0, 1, 2, 2, 0}, {0, 2, 0, 0, 2, 0, 0, 0, 2, 0, 0, 2, 0, 2, 0, 0, 1, 1, 0, 0, 0, 0, 2, 0, 2}, {0, 0, 0, 2, 2, 2, 2, 0, 2, 0, 0, 2, 0, 2, 2, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0}, {0, 0, 2, 0, 2, 1, 2, 0, 0, 0, 2, 2, 0, 0, 0, 0, 2, 0, 0, 2, 2, 0, 2, 0, 0}, {0, 0, 0, 0, 0, 1, 1, 2, 0, 2, 0, 0, 2, 2, 2, 0, 0, 2, 2, 0, 0, 0, 0, 2, 0}, {0, 0, 0, 2, 0, 2, 2, 0, 2, 2, 2, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 2, 2, 0}, {0, 2, 2, 2, 2, 0, 0, 2, 2, 2, 0, 0, 2, 2, 0, 0, 0, 2, 0, 1, 2, 0, 0, 1, 0}, {0, 0, 2, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 1, 0, 0, 0, 2, 0}, {0, 2, 2, 0, 0, 2, 2, 0, 0, 2, 2, 2, 2, 0, 0, 0, 2, 0, 2, 2, 0, 2, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 1, 0, 0, 0, 2, 2, 0}, {2, 0, 2, 0, 0, 0, 2, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2, 2, 2, 1}, {0, 2, 0, 0, 0, 2, 2, 2, 0, 2, 0, 0, 2, 2, 2, 0, 0, 0, 2, 0, 2, 0, 2, 2, 0}, {0, 0, 0, 2, 1, 2, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 2, 0, 0, 0, 2, 2, 2, 0, 2}, {0, 2, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 2, 2, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0}, {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 2, 2, 0, 2, 2, 0, 2, 0}, {0, 2, 1, 0, 1, 0, 2, 2, 0, 0, 0, 0, 0, 0, 2, 0, 2, 2, 2, 2, 0, 2, 2, 0, 0}, {2, 0, 0, 0, 0, 0, 2, 2, 0, 2, 0, 2, 2, 0, 0, 0, 0, 0, 2, 2, 2, 2, 0, 0, 0}, {0, 2, 2, 0, 2, 0, 2, 2, 0, 2, 0, 0, 2, 2, 0, 2, 0, 2, 0, 2, 0, 0, 1, 0, 2}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 2, 2, 2, 0, 0, 0}, {0, 0, 0, 0, 0, 2, 1, 0, 2, 0, 2, 0, 0, 0, 0, 2, 2, 0, 2, 2, 0, 0, 2, 0, 2}, {2, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0}, {0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 2, 2, 0, 0, 2, 1, 2, 0, 2, 0, 2, 2, 1, 2}, {2, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2}};

        int shortestDistance = new ShortestDistanceFromAllBuildings().shortestDistance(grid);
        System.out.println(shortestDistance);
    }

    public int shortestDistance(int[][] grid) {
        // for each building, contains distance to the each free coordinate
        List<int[][]> buildingDistances = new ArrayList<>();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    buildingDistances.add(initDistances(grid, row, col));
                }
            }
        }

        int shortestDistance = Integer.MAX_VALUE;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] != 0) {
                    continue;
                }

                int totalDistance = 0;
                for (int[][] distances : buildingDistances) {
                    int distance = distances[row][col];
                    if (distance == Integer.MAX_VALUE) {
                        totalDistance = -1;
                        break;
                    }
                    totalDistance += distance;
                }

                if (totalDistance != -1 && totalDistance < shortestDistance) {
                    shortestDistance = totalDistance;
                }
            }
        }

        return shortestDistance != Integer.MAX_VALUE ? shortestDistance : -1;
    }

    private int[][] initDistances(int[][] grid, int startRow, int starCol) {
        int[][] distances = new int[grid.length][grid[0].length];
        for (int[] row : distances) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startRow, starCol});
        distances[startRow][starCol] = 0;

        while (queue.size() > 0) {
            int[] p = queue.poll();
            int row = p[0], col = p[1], distance = distances[row][col];
            if (col > 0 && grid[row][col - 1] == 0 && distances[row][col - 1] == Integer.MAX_VALUE) {
                distances[row][col - 1] = distance + 1;
                queue.add(new int[]{row, col - 1});
            }

            if (col < grid[0].length - 1 && grid[row][col + 1] == 0 && distances[row][col + 1] == Integer.MAX_VALUE) {
                distances[row][col + 1] = distance + 1;
                queue.add(new int[]{row, col + 1});
            }

            if (row > 0 && grid[row - 1][col] == 0 && distances[row - 1][col] == Integer.MAX_VALUE) {
                distances[row - 1][col] = distance + 1;
                queue.add(new int[]{row - 1, col});
            }

            if (row < grid.length - 1 && grid[row + 1][col] == 0 && distances[row + 1][col] == Integer.MAX_VALUE) {
                distances[row + 1][col] = distance + 1;
                queue.add(new int[]{row + 1, col});
            }
        }

        return distances;
    }

}
