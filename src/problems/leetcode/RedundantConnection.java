package problems.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/redundant-connection/
 */
public class RedundantConnection {

    public static void main(String[] args) {
        int[][] edges = {{9, 10}, {5, 8}, {2, 6}, {1, 5}, {3, 8}, {4, 9}, {8, 10}, {4, 10}, {6, 8}, {7, 9}};
        int[] redundant = findRedundantConnectionDSU(edges);
        System.out.println(Arrays.toString(redundant));

//        DisjointSetUnion set = new DisjointSetUnion(10);
//        set.union(0, 1);
//        set.union(2, 3);
//        set.union(3, 4);
//        set.union(1, 2);
    }

    private static final int MAX_EDGE_VAL = 1000;

    public static int[] findRedundantConnectionDSU(int[][] edges) {
        DisjointSetUnion dsu = new DisjointSetUnion(MAX_EDGE_VAL + 1);
        for (int[] edge : edges) {
            if (!dsu.union(edge[0], edge[1])) {
                return edge;
            }
        }

        throw new AssertionError();
    }


//    public static void main(String[] args) {
//        DisjointSetUnion dsu = new DisjointSetUnion(10);
//        System.out.println(dsu.find(1));
//        System.out.println(dsu.find(2));
//        dsu.union(1, 2);
//        dsu.union(2, 3);
//        dsu.union(3, 4);
//
//        dsu.union(5, 6);
//        dsu.union(4, 6);
//
//        dsu.union(6, 7);
//    }

    private static class DisjointSetUnion {
        int[] parent;

        DisjointSetUnion(int size) {
            parent = new int[size];
            Arrays.fill(parent, -1);
        }

        int find(int x) {
            if (parent[x] < 0) {
                return x;
            }

            return (parent[x] = find(parent[x]));
        }

        boolean union(int n1, int n2) {
            int p1 = find(n1), p2 = find(n2);
            if (p1 == p2) {
                return false;
            }

            int rank1 = -parent[p1], rank2 = -parent[p2];
            if (rank1 < rank2) {
                parent[p1] = p2;
                parent[p2] -= rank1;
            } else {
                parent[p2] = p1;
                parent[p1] -= rank2;
            }

            return true;
        }
    }


    public static int[] findRedundantConnection(int[][] edges) {
        Map<Integer, Node> nodes = new HashMap<>();

        int[] result = null;
        for (int[] edge : edges) {
            Node from = nodes.get(edge[0]);

            if (from == null) {
                nodes.put(edge[0], from = new Node(edge[0]));
                Node to = nodes.get(edge[1]);
                if (to == null) {
                    nodes.put(edge[1], to = new Node(edge[1]));
                }

                connect(from, to);
                continue;
            }

            Node to = nodes.get(edge[1]);
            if (to == null) {
                nodes.put(edge[1], to = new Node(edge[1]));
                connect(from, to);
                continue;
            }

            if (isConnected(from, to, new HashSet<>())) {
                result = edge;
            } else {
                connect(from, to);
            }
        }

        return result;
    }

    private static void connect(Node from, Node to) {
        from.neighbors.add(to);
        to.neighbors.add(from);
    }

    private static boolean isConnected(Node from, Node to, Set<Node> visited) {
        if (from == to) {
            return true;
        }

        visited.add(from);

        for (Node neighbor : from.neighbors) {
            if (!visited.contains(neighbor) && isConnected(neighbor, to, visited)) {
                for (Node node : visited) {
                    if (node != from) {
                        connect(node, from);
                    }
                }

                return true;
            }
        }

        visited.remove(from);
//        for (Node node : visited) {
//            connect(node, from);
//        }
        return false;
    }

    private static class Node {
        int num;
        Set<Node> neighbors = new HashSet<>();

        Node(int num) {
            this.num = num;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "num=" + num +
                    ", neighbors=" + neighbors +
                    '}';
        }
    }

    /*


        [[1,3],[3,4],[1,5],[3,5],[2,3]]


            2
            |
        1 - 3 - 4
        | /
        5


     */

}
