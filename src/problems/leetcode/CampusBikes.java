package problems.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/campus-bikes/
 */
public class CampusBikes {

    public static void main(String[] args) {
        int[][] workers = {{0, 0}, {1, 1}, {2, 0}};
        int[][] bikes = {{1, 0}, {2, 2}, {2, 1}};
        int[] assignments = assignBikes(workers, bikes);
        System.out.println(Arrays.toString(assignments));

        List<Distance> distances = new ArrayList<>();
        distances.add(new Distance(1, 1, 0));
        distances.add(new Distance(0, 0, 0));
        distances.add(new Distance(1, 0, 0));
        distances.add(new Distance(0, 1, 0));

        Collections.sort(distances);
        for (Distance d : distances) {
            System.out.println(d);
        }


        // for each bike
        // assign it to the closest worker with smallest index


    }

    public static int[] assignBikes(int[][] workers, int[][] bikes) {
        int[] assignments = new int[workers.length];
        Arrays.fill(assignments, -1);
        List<Distance> distances = new ArrayList<>(workers.length * bikes.length);

        for (int i = 0; i < bikes.length; i++) {
            for (int j = 0; j < workers.length; j++) {
                distances.add(new Distance(i, j, manhattanDistance(bikes[i], workers[j])));
            }
        }

        Collections.sort(distances);

        boolean[] bikeAssignments = new boolean[bikes.length];
        int assigned = 0;
        for (Distance distance : distances) {
            int bike = distance.bike, worker = distance.worker;
            if (bikeAssignments[bike] || assignments[worker] != -1) {
                continue;
            }

            bikeAssignments[bike] = true;
            assignments[worker] = bike;

            if (++assigned == workers.length) {
                break;
            }
        }

        return assignments;
    }

    private static int manhattanDistance(int[] bike, int[] worker) {
        return Math.abs(bike[0] - worker[0]) + Math.abs(bike[1] - worker[1]);
    }

    private static class Distance implements Comparable<Distance> {
        final int bike;
        final int worker;
        final int distance;

        Distance(int bike, int worker, int distance) {
            this.bike = bike;
            this.worker = worker;
            this.distance = distance;
        }

        @Override
        public int compareTo(Distance other) {
            int cd = Integer.compare(this.distance, other.distance);
            if (cd != 0) {
                return cd;
            }

            int cb = Integer.compare(this.bike, other.bike);
            if (cb != 0) {
                return cb;
            }

            return Integer.compare(this.worker, other.worker);
        }

        @Override
        public String toString() {
            return "Distance{" +
                    "bike=" + bike +
                    ", worker=" + worker +
                    ", distance=" + distance +
                    '}';
        }
    }

}
