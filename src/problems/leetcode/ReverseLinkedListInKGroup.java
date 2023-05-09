package problems.leetcode;

/**
 * https://leetcode.com/problems/reverse-nodes-in-k-group
 */
public class ReverseLinkedListInKGroup {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // runtime: O(N)
    // space: O(N/k)
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k < 2) {
            return head;
        }

        // runtime: O(N)
        // space: O(1)
        ListNode n = head;
        for (int i = 0; i < k; i++) {
            if (n == null) {
                return head;
            }

            n = n.next;
        }

        // runtime: O(N)
        // space: O(1)
        ListNode prev = null, current = head, next;
        // prev will be the new head
        for (int i = 0; i < k; i++) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        // runtime: O(N)
        // space: O(N/k)
        // here, head is the tail of the reversed k-nodes
        head.next = reverseKGroup(current, k);

        return prev;
    }

}
