package problems.leetcode;

/**
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 */
public class RemoveNthNodeFromEndOfList {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "ListNode{" + "val=" + val + ", next=" + next + '}';
        }
    }


    private int i;

    public ListNode removeNthFromEnd(ListNode head, int n) {
        return remove(head, n);
    }

    private ListNode remove(ListNode node, int n) {
        if (node.next == null) {
            if (n == 1) {
                i = -1;
                return null;
            } else {
                i = 1;
                return node;
            }
        }

        ListNode next = remove(node.next, n);
        if (i == -1) {
            node.next = next;
            return node;
        } else if (i + 1 == n) {
            i = -1;
            return next;
        } else {
            i++;
            return node;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode newHead = new RemoveNthNodeFromEndOfList().removeNthFromEnd(head, 5);
        System.out.println(newHead);
    }

}
