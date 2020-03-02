package problems.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * https://leetcode.com/problems/network-delay-time/
 */
public class NetworkDelayTime {

    private List[] adjs;
    private Map<Integer, NodeDistance> distances = new HashMap<>();
    private PriorityQueue<NodeDistance> Q = new PriorityQueue<>();
    private Set<Integer> present = new HashSet<>();

    public int networkDelayTime(int[][] times, int n, int source) {
        buildAdjList(n, times);
        initSingleSource(n, source);

        while (Q.size() > 0) {
            NodeDistance nd = Q.poll();
            if (adjs[nd.target] != null) {
                for (Edge edge : (List<Edge>) adjs[nd.target]) {
                    relax(edge.source, edge.target, edge.cost);
                }
            }
        }

        int maxDist = 0;
        for (NodeDistance dist : distances.values()) {
            if (dist.target == source || !present.contains(dist.target)) {
                continue;
            } else if (dist.distance == Integer.MAX_VALUE) {
                return -1;
            }

            maxDist = Math.max(maxDist, dist.distance);
        }

        return maxDist;
    }

    private void buildAdjList(int n, int[][] times) {
        adjs = new List[n + 1];
        for (int[] edge : times) {
            List<Edge> adj = adjs[edge[0]];
            if (adj == null) {
                adjs[edge[0]] = adj = new ArrayList<>();
            }

            adj.add(new Edge(edge[0], edge[1], edge[2]));
        }
    }

    private void initSingleSource(int n, int source) {
        for (int node = 1; node <= n; node++) {
            present.add(node);
            NodeDistance nd = new NodeDistance(node, node == source ? 0 : Integer.MAX_VALUE);
            Q.add(nd);
            distances.put(node, nd);
        }
    }

    boolean relax(int u, int v, int cost) {
        NodeDistance ndv = distances.get(v);
        NodeDistance ndu = distances.get(u);
        if (ndv.distance > ndu.distance + cost) {
            NodeDistance ndv2 = new NodeDistance(v, ndu.distance + cost);
            Q.remove(ndv);
            Q.add(ndv2);
            distances.put(v, ndv2);

            return true;
        }

        return false;
    }

    private static class Edge {
        final int source;
        final int target;
        final int cost;

        Edge(int source, int target, int cost) {
            this.source = source;
            this.target = target;
            this.cost = cost;
        }
    }

    private static class NodeDistance
            implements Comparable<NodeDistance> {
        final int target;
        final int distance;

        public NodeDistance(int target, int distance) {
            this.target = target;
            this.distance = distance;
        }

        @Override
        public int compareTo(NodeDistance other) {
            int c = Integer.compare(this.distance, other.distance);
            return c != 0 ? c : Integer.compare(this.target, other.target);
        }
    }

    public static void main(String[] args) {
        int n = 4;
        int source = 2;
        int[][] times = new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int delayTime = new NetworkDelayTime().networkDelayTime(times, n, source);
        System.out.println(delayTime);
    }
}
