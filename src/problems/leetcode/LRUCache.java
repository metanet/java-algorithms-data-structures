package problems.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/lru-cache/
 */
public class LRUCache {

    private static class Node {
        final int key;
        final int value;
        private Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    ", next=" + next +
                    '}';
        }
    }

    private final Map<Integer, Node> map = new HashMap<>();
    private final int capacity;
    private Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }

        remove(node);
        add(node);

        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            remove(node);
        }

        node = new Node(key, value);
        map.put(key, node);
        add(node);

        if (map.size() > capacity) {
            map.remove(head.key);
            remove(head);
        }
    }

    private void add(Node node) {
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
            node.next = null;
        }
    }

    private void remove(Node node) {
        if (node == null) {
            throw new IllegalArgumentException();
        }

        if (head == node) {
            head = node.next;
            if (head != null) {
                head.prev = null;
            }
        }

        if (tail == node) {
            tail = node.prev;
            if (tail != null) {
                tail.next = null;
            }
        }

        if (node.prev != null) {
            node.prev.next = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        }

        node.next = null;
        node.prev = null;
    }


    public static void main(String[] args) {
        LRUCache cache1 = new LRUCache(10);
        cache1.put(10, 13);
        cache1.put(3, 17);
        cache1.put(6, 11);
        cache1.put(10, 5);
        cache1.put(9, 10);

        if (cache1.get(13) != -1) {
            throw new IllegalStateException();
        }

        cache1.put(2, 19);

        if (cache1.get(2) != 19) {
            throw new IllegalStateException();
        }

        if (cache1.get(3) != 17) {
            throw new IllegalStateException();
        }

        cache1.put(5, 25);

        if (cache1.get(8) != -1) {
            throw new IllegalStateException();
        }

        cache1.put(9, 22);
        cache1.put(5, 5);
        cache1.put(1, 30);

        if (cache1.get(11) != -1) {
            throw new IllegalStateException();
        }

        cache1.put(9, 12);

        if (cache1.get(7) != -1) {
            throw new IllegalStateException();
        }

        if (cache1.get(5) != 5) {
            throw new IllegalStateException();
        }

        if (cache1.get(8) != -1) {
            throw new IllegalStateException();
        }

        if (cache1.get(9) != 12) {
            throw new IllegalStateException();
        }

        cache1.put(4, 30);
        cache1.put(9, 3);

        if (cache1.get(9) != 3) {
            throw new IllegalStateException();
        }

        if (cache1.get(10) != 5) {
            throw new IllegalStateException();
        }

        if (cache1.get(10) != 5) {
            throw new IllegalStateException();
        }

        cache1.put(6, 14);
        cache1.put(3, 1);

        if (cache1.get(3) != 1) {
            throw new IllegalStateException();
        }

        cache1.put(10, 11);

        if (cache1.get(8) != -1) {
            throw new IllegalStateException();
        }

        cache1.put(2, 14);

        if (cache1.get(1) != 30) {
            throw new IllegalStateException();
        }

        if (cache1.get(5) != 5) {
            throw new IllegalStateException();
        }

        if (cache1.get(4) != 30) {
            throw new IllegalStateException();
        }

        cache1.put(11, 4);
        cache1.put(12, 24);
        cache1.put(5, 18);

        if (cache1.get(13) != -1) {
            throw new IllegalStateException();
        }

        cache1.put(7, 23);

        if (cache1.get(8) != -1) {
            throw new IllegalStateException();
        }

        if (cache1.get(12) != 24) {
            throw new IllegalStateException();
        }

        cache1.put(3, 27);
        cache1.put(2, 12);

        if (cache1.get(5) != 18) {
            throw new IllegalStateException();
        }

        cache1.put(2, 9);
        cache1.put(13, 14);
        cache1.put(8, 18);
        cache1.put(1, 7);

        if (cache1.get(6) != -1) {
            throw new IllegalStateException();
        }
    }

    /*
     *
     * capacity: 10
     *
     * [10,13],
     * [3,17],
     * [6,11],
     * [10,5],
     * [9,10],
     *
     * [13], -1
     *
     * [2,19],
     *
     * [2], 19
     * [3], 17
     *
     * [5,25],
     *
     * [8], -1
     *
     * [9,22],[5,5],[1,30],
     *
     * [11], -1
     *
     * [9,12],
     *
     * [7], -1
     * [5], 5
     * [8], -1
     * [9], 12
     *
     * [4,30],[9,3],
     *
     * [9], 3
     * [10], 5
     * [10], 5
     *
     * [6,14],[3,1],
     *
     * [3], 1
     *
     * [10,11],
     *
     * [8], -1
     *
     * [2,14],
     *
     * [1], 30
     * [5], 5
     * [4], 30
     *
     * [11,4],[12,24],[5,18],
     *
     * [13], -1
     *
     * [7,23],
     *
     * [8], -1
     * [12], 24
     *
     * [3,27],[2,12],
     *
     * [5], 18
     *
     * [2,9],[13,4],[8,18],[1,7],
     *
     * [6], -1 bu bizde 14 return etmis.
     */

}
