package problems.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * https://leetcode.com/problems/clone-graph/
 */
public class CloneGraph {

    private static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
        }

        public Node(int val, List<Node> neighbors) {
            this.val = val;
            this.neighbors = neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);

        Map<Node, Node> cloned = new HashMap<>();
        cloned.put(node, new Node(node.val, new ArrayList<>()));

        while (queue.size() > 0) {
            for (Node neighbour : queue.poll().neighbors) {
                if (cloned.containsKey(neighbour)) {
                    continue;
                }

                cloned.put(neighbour, new Node(neighbour.val, new ArrayList<>()));
                queue.add(neighbour);
            }
        }

        for (Map.Entry<Node, Node> e : cloned.entrySet()) {
            Node n = e.getKey(), c = e.getValue();
            for (Node neighbour : n.neighbors) {
                c.neighbors.add(cloned.get(neighbour));
            }
        }

        return cloned.get(node);
    }

}
