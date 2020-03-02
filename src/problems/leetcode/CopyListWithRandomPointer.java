package problems.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 */
public class CopyListWithRandomPointer {

    private static class Node {
        final int val;
        Node next;
        Node random;

        Node(int val, Node next, Node random) {
            this.val = val;
            this.next = next;
            this.random = random;
        }
    }

    public static Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Map<Node, Node> oldToNew = new HashMap<>();
        Node copyHead = copyRandomList(head, oldToNew);

        Node orgCurr = head, copyCurr = copyHead;
        for (int i = 0; i < oldToNew.size(); i++) {
            copyCurr.random = oldToNew.get(orgCurr.random);
            orgCurr = orgCurr.next;
            copyCurr = copyCurr.next;
        }

        return copyHead;
    }

    private static Node copyRandomList(Node node, Map<Node, Node> oldToNew) {
        if (node == null) {
            return null;
        }

        Node copyHead = new Node(node.val, null, null), copyTail = copyHead;
        oldToNew.put(node, copyHead);

        while (node != null) {
            Node next = node.next;
            if (next != null) {
                Node copy = new Node(next.val, null, null);
                copyTail.next = copy;
                copyTail = copy;
                oldToNew.put(next, copy);
            }

            node = next;
        }

        return copyHead;
    }

}
