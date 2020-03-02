package problems.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 */
public class BinaryTreeZigzagLevelOrderTraversal {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

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

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        Queue<List<TreeNode>> queue = new LinkedList<>();
        List<List<Integer>> traversals = new ArrayList<>();

        queue.add(Collections.singletonList(root));
        traversals.add(Collections.singletonList(root.val));

        while (!queue.isEmpty()) {
            List<TreeNode> nodesAtNextLevel = new ArrayList<>();
            List<Integer> traversal = new ArrayList<>();
            for (TreeNode nodeAtCurrentLevel : queue.remove()) {
                if (nodeAtCurrentLevel.left != null) {
                    nodesAtNextLevel.add(nodeAtCurrentLevel.left);
                    traversal.add(nodeAtCurrentLevel.left.val);
                }
                if (nodeAtCurrentLevel.right != null) {
                    nodesAtNextLevel.add(nodeAtCurrentLevel.right);
                    traversal.add(nodeAtCurrentLevel.right.val);
                }
            }

            if (traversal.size() > 0) {
                traversals.add(traversal);
            }

            if (nodesAtNextLevel.size() > 0) {
                queue.add(nodesAtNextLevel);
            }
        }

        for (int i = 1; i < traversals.size(); i += 2) {
            Collections.reverse(traversals.get(i));
        }

        return traversals;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        List<List<Integer>> traversals = zigzagLevelOrder(root);

        for (List<Integer> level : traversals) {
            System.out.println(level);
        }
    }

}
