package problems.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-postorder-traversal/
 */
public class BinaryTreePostOrderTraversalWithStack {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private static class VisitedNode {
        TreeNode node;
        boolean childrenVisited;

        VisitedNode(TreeNode node, boolean childrenVisited) {
            this.node = node;
            this.childrenVisited = childrenVisited;
        }
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        List<Integer> visited = new ArrayList<>();

        Deque<VisitedNode> stack = new ArrayDeque<>();
        stack.addLast(new VisitedNode(root, false));

        while (stack.size() > 0) {
            VisitedNode v = stack.removeLast();
            if (v.childrenVisited) {
                visited.add(v.node.val);
            } else {
                stack.addLast(new VisitedNode(v.node, true));

                // we will pop left child first...

                if (v.node.right != null) {
                    stack.addLast(new VisitedNode(v.node.right, false));
                }

                if (v.node.left != null) {
                    stack.addLast(new VisitedNode(v.node.left, false));
                }
            }
        }


        return visited;
    }

}
