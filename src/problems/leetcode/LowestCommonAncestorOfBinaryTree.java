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

        TreeNode ancestor = new LowestCommonAncestorOfBinaryTree().lowestCommonAncestor(root, new TreeNode(6), new TreeNode(4));

        System.out.println(ancestor.val);
    }

    private List<List<TreeNode>> knownPaths = new ArrayList<>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }

        lowestCommonAncestor(root, p, q, new ArrayList<>());

        assert knownPaths.size() == 2;

        List<TreeNode> path1 = knownPaths.get(0), path2 = knownPaths.get(1);
        TreeNode ancestor = path1.get(0);
        for (int i = 1; i < Math.min(path1.size(), path2.size()); i++) {
            TreeNode node1 = path1.get(i), node2 = path2.get(i);
            if (node1 == node2) {
                ancestor = node1;
            } else {
                break;
            }
        }

        return ancestor;
    }

    private void lowestCommonAncestor(TreeNode node, TreeNode p, TreeNode q, List<TreeNode> path) {
        if (node == null || knownPaths.size() == 2) {
            return;
        }

        path.add(node);

        if (node.val == p.val) {
            knownPaths.add(new ArrayList<>(path));
        } else if (node.val == q.val) {
            knownPaths.add(new ArrayList<>(path));
        }

        lowestCommonAncestor(node.left, p, q, path);
        lowestCommonAncestor(node.right, p, q, path);

        path.remove(path.size() - 1);
    }

}
