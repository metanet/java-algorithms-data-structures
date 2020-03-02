package problems.leetcode;

/**
 * https://leetcode.com/problems/rotate-list/
 */
public class RotateList {

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
                    ", next=" + next +
                    '}';
        }
    }

    private ListNode oldTail, newTail;

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }

        traverse(head, 1, k);

        if (newTail == null) {
            return head;
        }

        ListNode newHead = newTail.next;
        newTail.next = null;
        oldTail.next = head;

        return newHead;
    }

    private int traverse(ListNode node, int i, int k) {
        if (node.next == null) {
            oldTail = node;
            k = k % i;
            return k - 1;
        }

        int j = traverse(node.next, i + 1, k);
        if (j == 0) {
            newTail = node;
        }

        return j - 1;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        int k = 5;

        ListNode newHead = new RotateList().rotateRight(head, k);
        System.out.println(newHead);
    }


}
