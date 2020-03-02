package problems.leetcode;

/**
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 */
public class FlattenBinaryTreeToLinkedList {

    private static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    '}';
        }
    }

    private TreeNode head, tail;

    public void flatten(TreeNode node) {
        if (node == null) {
            return;
        }

        TreeNode left = node.left, right = node.right;
        node.left = null;
        node.right = null;

        if (head == null) {
            head = tail = node;
        } else {
            tail.right = node;
            tail = node;
        }


        flatten(left);
        flatten(right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);

        new FlattenBinaryTreeToLinkedList().flatten(root);
        System.out.println();
    }

}
