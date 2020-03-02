package problems.leetcode;

/**
 * https://leetcode.com/problems/intersection-of-two-linked-lists/
 */
public class IntersectionOfTwoLinkedLists {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode nodeA = headA, nodeB = headB;
        boolean nodeASwitched = false, nodeBSwitched = false;
        while (true) {
            if (nodeA == nodeB) {
                return nodeA;
            }

            nodeA = nodeA.next;
            nodeB = nodeB.next;

            if (nodeA == null) {
                if (nodeASwitched) {
                    return null;
                }

                nodeA = headB;
                nodeASwitched = true;
            }

            if (nodeB == null) {
                if (nodeBSwitched) {
                    return null;
                }

                nodeB = headA;
                nodeBSwitched = true;
            }
        }
    }

    public static void main(String[] args) {
        ListNode headA = new ListNode(4);
        headA.next = new ListNode(1);
        headA.next.next = new ListNode(8);
        headA.next.next.next = new ListNode(4);
        headA.next.next.next.next = new ListNode(5);

        ListNode headB = new ListNode(5);
        headB.next = new ListNode(0);
        headB.next.next = new ListNode(1);
        headB.next.next.next = headA.next.next;

        System.out.println(getIntersectionNode(headA, headB));
    }

}
