package problems.leetcode;

/**
 * https://leetcode.com/problems/odd-even-linked-list/
 */
public class OddEvenLinkedList {

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

    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode evenHead = head.next, oddTail = head, evenTail = head.next, current = evenTail.next;
        while (current != null) {
            oddTail.next = current;
            oddTail = current;
            ListNode nextEven = current.next;
            evenTail.next = nextEven;
            if (nextEven == null) {
                break;
            }

            evenTail = nextEven;
            current = nextEven.next;
        }

        oddTail.next = evenHead;
        evenTail.next = null;

        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(1);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next = new ListNode(4);
//        head.next.next.next.next.next.next = new ListNode(7);

        ListNode newHead = oddEvenList(head);
        System.out.println(newHead);
    }

    /*

    Input: 2->1->3->5->6->4->7->NULL
           o  e



    Output: 2->3->6->7->1->5->4->NULL



     */

}
