package problems.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/subtree-of-another-tree/
 */
public class SubTreeOfAnotherTree {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode s = new TreeNode(3);
        s.left = new TreeNode(4);
        s.right = new TreeNode(5);
        s.left.left = new TreeNode(1);
        s.left.right = new TreeNode(2);
//        s.left.left.left = new TreeNode(0);

        TreeNode t = new TreeNode(4);
        t.left = new TreeNode(1);
        t.right = new TreeNode(2);

        System.out.println(isSubtree(s, t));
    }

    public static boolean isSubtree(TreeNode s, TreeNode t) {
        List<TreeNode> nodes = new ArrayList<>();
        find(s, t, nodes);
        for (TreeNode n : nodes) {
            if (isSameTree(n, t)) {
                return true;
            }
        }

        return false;
    }

    private static void find(TreeNode node, TreeNode target, List<TreeNode> found) {
        if (node == null) {
            return;
        }

        if (node.val == target.val) {
            found.add(node);
        }

        find(node.left, target, found);
        find(node.right, target, found);
    }

    private static boolean isSameTree(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        } else if (node1 != null && node2 == null) {
            return false;
        } else if (node1 == null) {
            return false;
        } else if (node1.val != node2.val) {
            return false;
        }

        return isSameTree(node1.left, node2.left) && isSameTree(node1.right, node2.right);
    }
}
