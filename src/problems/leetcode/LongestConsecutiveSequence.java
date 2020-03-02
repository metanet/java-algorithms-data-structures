package problems.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-consecutive-sequence/
 */
public class LongestConsecutiveSequence {

    private static class Node {
        int num, count;
        Node next;

        Node(int num) {
            this.num = num;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "num=" + num +
                    ", next=" + next +
                    '}';
        }
    }

    public static int longestConsecutive(int[] nums) {
        if (nums == null) {
            return 0;
        } else if (nums.length < 2) {
            return nums.length;
        }

        Map<Integer, Node> nodes = new HashMap<>();

        for (int num : nums) {
            if (nodes.containsKey(num)) {
                continue;
            }

            Node node = new Node(num);
            nodes.put(num, node);
            Node prev = nodes.get(num - 1);
            if (prev != null) {
                prev.next = node;
            }
            node.next = nodes.get(num + 1);
        }

        int longest = 0;
        for (Node node : nodes.values()) {
            longest = Math.max(longest, count(node));
        }

        return longest;
    }

    private static int count(Node node) {
        if (node == null) {
            return 0;
        } else if (node.count > 0) {
            return node.count;
        }

        node.count = 1 + count(node.next);
        return node.count;
    }

    public static void main(String[] args) {
        int[] nums = {100, 4, 200, 1, 3, 2};

        System.out.println(longestConsecutive(nums));
    }

}
