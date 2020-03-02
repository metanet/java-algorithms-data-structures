package problems.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/minimum-height-trees/
 */
public class MinimumHeightTrees {

    private Set[] adjs;

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            return Collections.singletonList(0);
        }

        buildAdjs(n, edges);

        Queue<Integer> leafs = new LinkedList<>();

        for (int node = 0; node < n; node++) {
            if (adjs[node].size() == 1) {
                leafs.add(node);
            }
        }

        List<Integer> result = null;
        while (leafs.size() > 0) {
            List<Integer> list = new ArrayList<>();
            int size = leafs.size();
            for (int i = 0; i < size; i++) {
                int leaf = leafs.poll();
                list.add(leaf);

                for (int neigh : ((Set<Integer>) adjs[leaf])) {
                    adjs[neigh].remove(leaf);

                    if (adjs[neigh].size() == 1) {
                        leafs.offer(neigh);
                    }
                }
            }

            result = list;
        }

        return result;
    }

    private void buildAdjs(int n, int[][] edges) {
        adjs = new Set[n];
        for (int[] e : edges) {
            addEdge(e[0], e[1]);
            addEdge(e[1], e[0]);
        }
    }

    private void addEdge(int v1, int v2) {
        if (adjs[v1] == null) {
            adjs[v1] = new HashSet();
        }
        adjs[v1].add(v2);
    }

    public static void main(String[] args) {
        //                int numNodes = 4;
        //                int[][] edges = new int[][]{{1, 0}, {1, 2}, {1, 3}};

        //        int numNodes = 6;
        //        int[][] edges = new int[][]{{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}};

//        int numNodes = 6;
//        int[][] edges = new int[][]{{0, 1}, {0, 2}, {0, 3}, {3, 4}, {4, 5}};

        int numNodes = 4;
        int[][] edges = new int[][]{{0, 1}, {1, 2}, {2, 3}};

        System.out.println(new MinimumHeightTrees().findMinHeightTrees(numNodes, edges));
    }

}
