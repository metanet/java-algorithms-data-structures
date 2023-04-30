package problems.leetcode;

/**
 * https://leetcode.com/problems/reverse-linked-list/
 */
public class ReverseLinkedList {

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

    // 1 - > 2  -> 3 -> 4

    // head = 1
    // curr = head
    // prev = null
    
    // next = curr.next 
    // curr.next = prev
    // prev = curr
    // curr = next

    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        System.out.println(head);
        head = reverseList(head);
        System.out.println(head);
    }
}
