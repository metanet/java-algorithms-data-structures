package problems.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/merge-k-sorted-lists/description/
 */
public class MergeKSortedLists {

    private static class ListNode {
        final int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    // runtime: O(NlgN) where N is number of lists
    // space: O(N)
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> heap = new PriorityQueue<>(new Comparator<ListNode>() {
            public int compare(ListNode a, ListNode b) {
                return Integer.compare(a.val, b.val);
            }
        });

        for (ListNode node : lists) {
            if (node != null) {
                heap.add(node);
            }
        }
        
        ListNode head = null, tail = null;
        while (!heap.isEmpty()) {
            ListNode node = heap.poll();
            ListNode next = node.next;
            node.next = null;
            if (head == null) {
                head = tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
            if (next != null) {
                heap.add(next);
            }
        }
        
        return head;
    }

}
