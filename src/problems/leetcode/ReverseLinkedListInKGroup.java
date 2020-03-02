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

    public ListNode reverseKGroup(ListNode node, int k) {
        if (node == null || node.next == null) {
            return node;
        }

        ListNode n = node;
        for (int i = 0; i < k; i++) {
            if (n == null) {
                return node;
            }

            n = n.next;
        }

        ListNode prev = null, current = node, next;
        for (int i = 0; i < k; i++) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        node.next = reverseKGroup(current, k);

        return prev;
    }

}
