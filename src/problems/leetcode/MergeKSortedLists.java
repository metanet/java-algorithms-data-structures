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

    private static class ListNodeHolder {
        final ListNode node;
        final int index;

        ListNodeHolder(ListNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNodeHolder> heap = new PriorityQueue<>(Comparator.comparingInt(holder -> holder.node.val));

        for (int i = 0; i < lists.length; i++) {
            ListNode node = next(lists, i);
            if (node != null) {
                heap.add(new ListNodeHolder(node, i));
            }
        }

        ListNode head = null, tail = null;
        while (heap.size() > 0) {
            ListNodeHolder holder = heap.poll();
            if (head == null) {
                head = tail = holder.node;
            } else {
                tail.next = holder.node;
                tail = holder.node;
            }

            ListNode next = next(lists, holder.index);
            if (next != null) {
                heap.add(new ListNodeHolder(next, holder.index));
            }
        }

        return head;
    }

    private static ListNode next(ListNode[] lists, int index) {
        ListNode next = lists[index];
        if (next != null) {
            lists[index] = next.next;
            next.next = null;
        }

        return next;
    }
}
