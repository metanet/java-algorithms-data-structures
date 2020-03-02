package problems.leetcode;

/**
 * https://leetcode.com/problems/add-two-numbers/
 */
public class AddTwoNumbers {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }

        int sum = l1.val + l2.val, carry = 0;
        if (sum >= 10) {
            sum -= 10;
            carry = 1;
        }

        ListNode sumHead = new ListNode(sum), sumTail = sumHead;

        l1 = l1.next;
        l2 = l2.next;

        while (l1 != null || l2 != null) {
            sum = carry + (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0);

            if (sum >= 10) {
                sum -= 10;
                carry = 1;
            } else {
                carry = 0;
            }

            sumTail.next = new ListNode(sum);
            sumTail = sumTail.next;

            if (l1 != null) {
                l1 = l1.next;
            }

            if (l2 != null) {
                l2 = l2.next;
            }
        }

        if (carry > 0) {
            sumTail.next = new ListNode(carry);
        }

        return sumHead;
    }

}
