package problems.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/is-graph-bipartite/
 */
public class IsGraphBipartite {

    public static void main(String[] args) {
        //        int[][] graph = new int[][]{{1, 3}, {0, 2}, {1, 3}, {0, 2}};
        //        int[][] graph = new int[][]{{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}};
        int[][] graph = new int[][]{{}, {3}, {}, {1}, {}};
        System.out.println(new IsGraphBipartite().isBipartite(graph));
    }

    private enum Color {
        NONE, BLUE, RED
    }

    private Map<Integer, Color> nodeColors = new HashMap<>();

    public boolean isBipartite(int[][] graph) {
        for (int node = 0; node < graph.length; ++node) {
            nodeColors.put(node, Color.NONE);
        }

        for (int node = 0; node < graph.length; ++node) {
            if (nodeColors.get(node) == Color.NONE) {
                if (!dfs(node, graph, Color.BLUE)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean dfs(int root, int[][] graph, Color desiredColor) {
        if (nodeColors.get(root) != Color.NONE) {
            return nodeColors.get(root) == desiredColor;
        }

        nodeColors.put(root, desiredColor);

        boolean bipartite = true;
        for (int edge : graph[root]) {
            bipartite &= dfs(edge, graph, desiredColor == Color.BLUE ? Color.RED : Color.BLUE);
        }

        return bipartite;
    }

}
