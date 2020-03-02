package problems.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/evaluate-division/
 */
public class EvaluateDivision {

    public static void main(String[] args) {
        List<List<String>> equations = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c")
                , Arrays.asList("c", "d"));
        double[] values = {2.0, 3.0, 4.0};
        List<List<String>> queries = Arrays.asList(Arrays.asList("a", "d"), Arrays.asList("b", "a"),
                Arrays.asList("a", "e"), Arrays.asList("a", "a"), Arrays.asList("x", "x"));

        double[] results = new EvaluateDivision().calcEquation(equations, values, queries);
        System.out.println(Arrays.toString(results));
    }


    private static class Edge {
        String from, to;
        double val;
        Edge prev;

        Edge(String from, String to, double val, Edge prev) {
            this.from = from;
            this.to = to;
            this.val = val;
            this.prev = prev;
        }
    }

    private Map<String, Map<String, Double>> allEdges = new HashMap<>();


    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            double val = values[i];

            String from = equation.get(0), to = equation.get(1);
            addEdge(from, to, val);
            addEdge(to, from, 1 / val);
        }

        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> q = queries.get(i);
            results[i] = query(q.get(0), q.get(1));
        }

        return results;
    }

    private void addEdge(String from, String to, double val) {
        Map<String, Double> neighbors = allEdges.get(from);
        if (neighbors == null) {
            allEdges.put(from, (neighbors = new HashMap<>()));
        }

        neighbors.putIfAbsent(to, val);
    }

    private double query(String from, String to) {
        if (from.equals(to)) {
            return allEdges.containsKey(from) ? 1 : -1;
        }

        Queue<Edge> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        visited.add(from);
        queue.add(new Edge(null, from, 1, null));

        while (queue.size() > 0) {
            Edge edge = queue.poll();

            Map<String, Double> neighbors = allEdges.get(edge.to);
            if (neighbors != null) {
                for (Map.Entry<String, Double> e : neighbors.entrySet()) {
                    String neighbour = e.getKey();
                    double val = e.getValue();

                    if (visited.add(neighbour)) {
                        Edge newEdge = new Edge(edge.to, neighbour, val, edge);
                        if (neighbour.equals(to)) {
                            return calculate(newEdge);
                        }

                        queue.add(newEdge);
                    }
                }
            }
        }

        return -1;
    }

    private double calculate(Edge edge) {
        Edge target = edge;
        double val = 1;
        while (edge != null) {
            val *= edge.val;

            if (edge.from != null) {
                addEdge(edge.from, target.to, val);
                addEdge(target.to, edge.from, 1 / val);
            }

            edge = edge.prev;
        }

        target = target.prev;
        while (target != null && target.from != null) {
            calculate(target);
            target = target.prev;
        }

        return val;
    }


    /*

    a -> b : 2.0
    b -> a : 0.5
    b -> c : 3.0
    c -> b : 0.3

    query: a -> c

    b -> c : 3.0
    a -> b : 2.0
    _ -> a : 1.0



    a -> b : 2
    b -> c : 3
    c -> d : 4
    d -> e : 5

    d -> e : 5
    c -> d : 4
    b -> c : 3
    a -> b : 2
    _ -> a -> 1


    c -> e : 20
    b -> e : 60
    a -> e : 120







     */

}
