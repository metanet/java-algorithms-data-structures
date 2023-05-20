package problems.leetcode;

// https://leetcode.com/problems/remove-linked-list-elements/
public class RemoveLinkedListElements {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return next != null ? (val + " -> " + next.toString()) : String.valueOf(val);
        }
    }

    public ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }

        ListNode node = head;
        while (node != null) {
            if (node.next != null && node.next.val == val) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }

        return head;
    }

}
