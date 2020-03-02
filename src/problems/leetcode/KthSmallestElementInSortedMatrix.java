package problems.leetcode;

import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix
 */
public class KthSmallestElementInSortedMatrix {

    public static void main(String[] args) {
        int[][] matrix = {{1, 2}, {1, 3}};
        int k = 2;
        System.out.println(kthSmallest(matrix, k));
    }

    public static int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Num> heap = new PriorityQueue<>();
        int count = 0;
        assert k > 0;
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            heap.add(new Num(matrix[i][0], i, 0));
        }

        Num num = null;
        while (count < k) {
            num = heap.poll();
            if (num.col < n - 1) {
                heap.add(new Num(matrix[num.row][num.col + 1], num.row, num.col + 1));
            }

            count++;
        }

        return num.num;
    }

    private static class Num implements Comparable<Num> {
        int num;
        int row;
        int col;

        Num(int num, int row, int col) {
            this.num = num;
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(Num other) {
            return Integer.compare(this.num, other.num);
        }
    }

}
