package problems.leetcode;

/**
 * https://leetcode.com/problems/sort-list/
 */
public class SortList {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    '}';
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(-1);
        head.next = new ListNode(5);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(0);

        ListNode sorted = sortList(head);
        System.out.println();
    }

    public static ListNode sortList(ListNode head) {
        return mergeSort(head, 0, count(head) - 1);
    }

    private static ListNode mergeSort(ListNode head, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            ListNode mid = split(head, m - l + 1);
            ListNode merged1 = mergeSort(head, l, m);
            ListNode merged2 = mergeSort(mid, m + 1, r);

            return merge(merged1, merged2);
        }

        return head;
    }

    private static ListNode split(ListNode head, int count) {
        ListNode prev = null;
        while (count-- > 0) {
            prev = head;
            head = head.next;
        }

        prev.next = null;
        return head;
    }

    private static ListNode merge(ListNode node1, ListNode node2) {
        if (node1 == null && node2 == null) {
            return null;
        }

        ListNode head = null, tail = null;

        while (node1 != null && node2 != null) {
            if (node1.val < node2.val) {
                if (head == null) {
                    head = tail = node1;
                } else {
                    tail.next = node1;
                    tail = node1;
                }

                node1 = node1.next;
                tail.next = null;
            } else {
                if (head == null) {
                    head = tail = node2;
                } else {
                    tail.next = node2;
                    tail = node2;
                }

                node2 = node2.next;
                tail.next = null;
            }
        }

        while (node1 != null) {
            tail.next = node1;
            tail = node1;
            node1 = node1.next;
            tail.next = null;
        }

        while (node2 != null) {
            tail.next = node2;
            tail = node2;
            node2 = node2.next;
            tail.next = null;
        }

        return head;
    }

    private static int count(ListNode node) {
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
        }

        return count;
    }

}
