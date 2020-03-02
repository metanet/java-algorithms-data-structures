package problems.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/cut-off-trees-for-golf-event/
 */
public class CutOffTreesForGolfEvent {

    public static void main(String[] args) {
        List<List<Integer>> forest = Arrays.asList(Arrays.asList(2, 2, 3), Arrays.asList(0, 0, 4), Arrays.asList(7, 6, 5));
        System.out.println(cutOffTree(forest));
    }

    private static final int[] HORIZONTAL_MOVES = {-1, 1, 0, 0};
    private static final int[] VERTICAL_MOVES = {0, 0, -1, 1};

    public static int cutOffTree(List<List<Integer>> forest) {
        List<int[]> trees = new ArrayList<>();
        for (int i = 0; i < forest.size(); ++i) {
            for (int j = 0; j < forest.get(0).size(); ++j) {
                int v = forest.get(i).get(j);
                if (v > 1) {
                    trees.add(new int[]{v, i, j});
                }
            }
        }

        trees.sort(Comparator.comparingInt(a -> a[0]));

        int steps = 0, i = 0, j = 0;
        for (int[] tree : trees) {
            int distance = distanceBFS(forest, i, j, tree[1], tree[2]);
            if (distance < 0) {
                return -1;
            }
            steps += distance;
            i = tree[1];
            j = tree[2];
        }
        return steps;
    }

    private static int distanceBFS(List<List<Integer>> forest, int iSrc, int jSrc, int iDest, int jDest) {
        int m = forest.size(), n = forest.get(0).size();
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{iSrc, jSrc, 0});
        boolean[][] seen = new boolean[m][n];
        seen[iSrc][jSrc] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int distance = cur[2];
            if (cur[0] == iDest && cur[1] == jDest) {
                return distance;
            }

            for (int k = 0; k < HORIZONTAL_MOVES.length; ++k) {
                int iNext = cur[0] + HORIZONTAL_MOVES[k], jNext = cur[1] + VERTICAL_MOVES[k];
                if (iNext >= 0 && iNext < m && jNext >= 0 && jNext < n && !seen[iNext][jNext]
                        && forest.get(iNext).get(jNext) > 0) {
                    seen[iNext][jNext] = true;
                    queue.add(new int[]{iNext, jNext, distance + 1});
                }
            }
        }

        return -1;
    }

}
