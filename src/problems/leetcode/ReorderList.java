package problems.leetcode;

/**
 * https://leetcode.com/problems/reorder-list/
 */
public class ReorderList {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "ListNode{" + "val=" + val + '}';
        }

        public void print() {
            System.out.println(val);
            if (next != null) {
                next.print();
            }
        }
    }

    public static void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        ListNode middle = findMiddle(head);
        middle = reverseList(middle);
        mergeLists(head, middle);
    }

    private static ListNode findMiddle(ListNode head) {
        // only one element would be hard in this case, assume already at least two elements
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode middle = slow.next;
        slow.next = null;
        return middle;
    }

    private static ListNode reverseList(ListNode head) {
        ListNode prev = null, current = head;
        while (current != null) {
            ListNode nxt = current.next;
            current.next = prev;
            prev = current;
            current = nxt;
        }

        return prev;
    }

    private static ListNode mergeLists(ListNode first, ListNode second) {
        ListNode dummyHead = new ListNode(0), tail = dummyHead;

        while (first != null && second != null) {
            tail.next = first;
            first = first.next;
            tail = tail.next;
            tail.next = second;
            second = second.next;
            tail = tail.next;
        }

        if (first != null) {
            tail.next = first;
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        n1.next = new ListNode(2);
        n1.next.next = new ListNode(3);
        n1.next.next.next = new ListNode(4);
//        n1.next.next.next.next = new ListNode(5);

        reorderList(n1);

        n1.print();
    }
}
