package problems.leetcode;

/**
 * https://leetcode.com/problems/swap-nodes-in-pairs/
 */
public class SwapNodesInPairs {

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

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = head, newHead = head.next, n1 = head.next.next;

        newHead.next = head;
        head.next = n1;

        while (n1 != null && n1.next != null) {
            ListNode n2 = n1.next;
            ListNode n3 = n1.next.next;
            prev.next = n2;
            n2.next = n1;
            n1.next = n3;
            prev = n1;
            n1 = n3;
        }

        return newHead;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode newHead = swapPairs(head);
        System.out.println(newHead);
    }

}
