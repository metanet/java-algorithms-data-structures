package problems.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 */
public class LowestCommonAncestorOfBinaryTree {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "TreeNode{" + "val=" + val + '}';
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        TreeNode ancestor = new LowestCommonAncestorOfBinaryTree().lowestCommonAncestor(root, new TreeNode(6),
                new TreeNode(4));

        System.out.println(ancestor.val);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }

        TreeNode l = lowestCommonAncestor(root.left, p, q);
        TreeNode r = lowestCommonAncestor(root.right, p, q);
        if (l == null) {
            return r;
        } else if (r == null) {
            return l;
        }
        
        return root;
    }

    private TreeNode ancestor;

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        traverse(root, p.val, q.val);
        return ancestor;
    }

    private boolean traverse(TreeNode root, int p, int q) {
        if (root == null) {
            return false;
        }

        if (root.val < Math.min(p, q)) {
            return traverse(root.right, p, q);
        } else if (root.val > Math.max(p, q)) {
            return traverse(root.left, p, q);
        }

        boolean foundHere = (root.val == p || root.val == q);
        boolean foundLeft = traverse(root.left, p, q);
        boolean foundRight = traverse(root.right, p, q);
        // System.out.println("val=" + root.val + ", here=" + foundHere + ", left=" +
        // foundLeft + ", right=" + foundRight);
        if ((foundHere && (foundLeft || foundRight)) || (foundLeft && foundRight)) {
            ancestor = root;
        }

        return foundHere || foundLeft || foundRight;
    }
}
