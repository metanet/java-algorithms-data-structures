package problems.leetcode;

/**
 * https://leetcode.com/problems/palindrome-linked-list/
 */
public class PalindromeLinkedList {

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

    private boolean palindrome = true;

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        travel(head, head);
        return palindrome;
    }

    private ListNode travel(ListNode node, ListNode head) {
        if (node == null) {
            return head;
        }

        head = travel(node.next, head);
        if (node.val != head.val) {
            palindrome = false;
        }

        return head.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
//        head.next = new ListNode(2);
//        head.next.next = new ListNode(3);
//        head.next.next.next = new ListNode(4);
//        head.next.next.next.next = new ListNode(1);

        System.out.println(new PalindromeLinkedList().isPalindrome(head));
    }

}
