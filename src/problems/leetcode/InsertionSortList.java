package problems.leetcode;

/**
 * https://leetcode.com/problems/insertion-sort-list/
 */
public class InsertionSortList {

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
                    //", next=" + next +
                    '}';
        }
    }

    public static ListNode insertionSortList(ListNode head) {
        boolean swap;
        int sorted = -1;
        do {
            ListNode node = head;
            swap = false;
            int index = -1;
            while (node != null && (sorted == -1 || index < sorted - 1)) {
                if (node.next != null && node.val > node.next.val) {
                    int t = node.val;
                    node.val = node.next.val;
                    node.next.val = t;
                    swap = true;
                }

                node = node.next;
                index++;
            }

            sorted = index;
        } while (swap);

        return head;
    }

    public static ListNode insertionSortList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode preHead = new ListNode(Integer.MIN_VALUE), current = head;
        while (current != null) {
            ListNode prev = preHead;
            while (prev.next != null && prev.next.val < current.val) {
                prev = prev.next;
            }

            ListNode next = current.next;
            current.next = prev.next;
            prev.next = current;
            current = next;
        }

        return preHead.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(5);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(0);
        head.next.next.next.next.next = new ListNode(2);

        head = insertionSortList2(head);

        ListNode node = head;
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

}
