package problems.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/discuss/interview-question/381172/Google-or-Phone-Screen-or-Sort-a-2D-Array
 */
public class Sort2DArray {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{5, 12, 17, 21, 23}, {1, 2, 4, 6, 8}, {12, 14, 18, 19, 27}, {3, 7, 9, 15, 25}};
        sort(matrix);

        for (int row = 0; row < matrix.length; row++) {
            System.out.println(Arrays.toString(matrix[row]));
        }
    }

    private static void sort(int[][] matrix) {
        int numRows = matrix.length, numCols = matrix[0].length;
        PriorityQueue<ValueHolder> heap = new PriorityQueue<>();
        // for each row, which col we have have put values into heap
        Map<Integer, Integer> rowsToCols = new HashMap<>();

        for (int row = 0; row < numRows; row++) {
            heap.offer(new ValueHolder(matrix[row][0], row, 0));
            rowsToCols.put(row, 0);
        }

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                ValueHolder holder = heap.poll();
                assert holder != null;

                if (rowsToCols.get(row) < col) {
                    // before updating the matrix, we need to ensure that the value at matrix[row,col] is put in heap
                    heap.offer(new ValueHolder(matrix[row][col], row, col));
                    rowsToCols.put(row, col);
                }

                matrix[row][col] = holder.value;

                int nextCol = holder.col + 1;
                if (holder.col < numCols - 1 && rowsToCols.get(holder.row) < nextCol) {
                    // put the next col of the current [row, col] to the heap
                    heap.offer(new ValueHolder(matrix[holder.row][nextCol], holder.row, nextCol));
                    rowsToCols.put(holder.row, nextCol);
                }
            }
        }
    }

    private static class ValueHolder
            implements Comparable<ValueHolder> {
        final int value;
        final int row;
        final int col;

        ValueHolder(int value, int row, int col) {
            this.value = value;
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(ValueHolder other) {
            return Integer.compare(this.value, other.value);
        }

        @Override
        public String toString() {
            return "ValueHolder{" + "value=" + value + ", row=" + row + ", col=" + col + '}';
        }
    }

}
