package problems.leetcode;

/**
 * https://leetcode.com/problems/linked-list-cycle/
 */
public class LinkedListCycle {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next;
            fast = fast.next;
            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

}
