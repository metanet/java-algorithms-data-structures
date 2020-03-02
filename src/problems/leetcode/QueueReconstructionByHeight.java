package problems.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.ToIntFunction;

/**
 * https://leetcode.com/problems/queue-reconstruction-by-height/
 */
public class QueueReconstructionByHeight {

    public static int[][] reconstructQueue(int[][] people) {
        // sort by descending heights,
        // if heights equal, sort by ascending counts

        Comparator<int[]> comparator = Comparator.comparingInt((ToIntFunction<int[]>) person -> person[0])
                .reversed().thenComparingInt(person -> person[1]);
        Arrays.sort(people, comparator);

        // The shorter people are invisible for the taller ones,
        // and hence we can first arrange the tallest guys as if there was no one else.
        // After that, we can arrange shorter people by respecting their count values...
        List<int[]> queue = new LinkedList<>();
        for (int[] person : people) {
            int index = person[1];
            queue.add(index, person);
        }

        return queue.toArray(new int[people.length][2]);
    }

    public static void main(String[] args) {
        int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        int[][] queue = reconstructQueue(people);
        for (int[] person : queue) {
            System.out.println(Arrays.toString(person));
        }
    }

    /*

    Input : [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

    Output: [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]

    [7,0]
    [7,0] [4,4]
    [7,0] [4,4] [7,1]
    [5,0] [7,0] [4,4] [7,1]
    [5,0] [7,0] [6,1] [4,4] [7,1]
    [5,0] [7,0] [6,1] [5,2] [4,4], [7,1]





    [5,0] [7,0] [5, 2] [6,1] [4, 4] [7,1]


     */

}
