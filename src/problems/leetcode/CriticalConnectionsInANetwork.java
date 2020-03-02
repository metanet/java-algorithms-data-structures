package problems.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/critical-connections-in-a-network/
 */
public class CriticalConnectionsInANetwork {

    private int time;
    private Map<Integer, List<Integer>> allEdges = new HashMap<>();
    private List<List<Integer>> criticalEdges = new ArrayList<>();
    private boolean[] visited;
    private int[] discoveryTimes;
    private int[] lowestDiscoveryTimes;
    private int[] parents;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        for (List<Integer> edge : connections) {
            Integer v1 = edge.get(0), v2 = edge.get(1);
            allEdges.computeIfAbsent(v1, v -> new ArrayList<>()).add(v2);
            allEdges.computeIfAbsent(v2, v -> new ArrayList<>()).add(v1);
        }

        visited = new boolean[n];
        discoveryTimes = new int[n];
        lowestDiscoveryTimes = new int[n];
        parents = new int[n];

        Arrays.fill(parents, -1);

        dfs(0);

        return criticalEdges;
    }

    // u: The vertex to be visited
    // visited[]: Already visited vertices
    // discoveryTimes[u]: Discovery time of u
    // lowestDiscoveryTimes[u]: Earliest discovery time of any vertex in u's subtree
    // parent[]: Parent vertex of each vertex
    private void dfs(int u) {
        visited[u] = true;
        ++time;
        discoveryTimes[u] = time;
        lowestDiscoveryTimes[u] = time;

        System.out.println("Visiting " + u + " at time: " + time);

        for (int v : allEdges.get(u)) {
            if (!visited[v]) {
                parents[v] = u;

                System.out.println(v + " from " + u + " is not visited...");
                dfs(v);

                // Check if v's subtree has an edge to any ancestor of u
                if (lowestDiscoveryTimes[v] < lowestDiscoveryTimes[u]) {
                    // if this condition holds, there is a shorter path to v
                    // from some ancestor of u...

                    System.out.println("> lowestDiscoveryTimes[" + v + "]=" + lowestDiscoveryTimes[v] + ", lowestDiscoveryTimes[" + u + "]=" + lowestDiscoveryTimes[u]);
                    lowestDiscoveryTimes[u] = lowestDiscoveryTimes[v];
                    System.out.println(u + "'s new lowestDiscoveryTime: " + lowestDiscoveryTimes[u]);
                }

                // If the lowest vertex reachable from subtree
                // under v is below u in DFS tree, then u-v is
                // a bridge
                if (lowestDiscoveryTimes[v] > discoveryTimes[u]) {
                    System.out.println("Critical edge: " + u + " to " + v + " because lowestDiscoveryTimes[" + v + "]=" + lowestDiscoveryTimes[v] + " > discoveryTimes[" + u + "]=" + discoveryTimes[u]);
                    criticalEdges.add(Arrays.asList(u, v));
                }
            } else if (v != parents[u]) {
                // u has a connection to v and v is not u's parent
                System.out.println(v + " from " + u + " is already visited and it is not " + u + "'s parent...");

                // Update lowestDiscoveryTimes value of u for parent function calls.
                if (discoveryTimes[v] < lowestDiscoveryTimes[u]) {
                    // if v's discovery time is smaller than u's lowest discovery time,
                    // then there is a shorter path to u from v.

                    System.out.println("# lowestDiscoveryTimes[" + u + "]=" + lowestDiscoveryTimes[u] + ", discoveryTimes[" + v + "]=" + discoveryTimes[v]);
                    lowestDiscoveryTimes[u] = discoveryTimes[v];
                    System.out.println(u + "'s new lowestDiscoveryTime: " + lowestDiscoveryTimes[u]);
                }
            }
        }

        System.out.println(u + " is done...");
    }


    public static void main(String[] args) {
        int n = 4;
        List<List<Integer>> connections = new ArrayList<>();
        connections.add(Arrays.asList(0, 1));
        connections.add(Arrays.asList(1, 2));
        connections.add(Arrays.asList(2, 0));
        connections.add(Arrays.asList(1, 3));

        List<List<Integer>> critical = new CriticalConnectionsInANetwork().criticalConnections(n, connections);

        for (List<Integer> edge : critical) {
            System.out.println("CRITICAL: " + edge);
        }
    }
}
