package problems.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal
 */
public class BinaryTreeLevelOrderTraversal {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        levelOrder(root, 1, levels);
        return levels;
    }

    public void levelOrder(TreeNode node, int currentLevel, List<List<Integer>> levels) {
        if (node == null) {
            return;
        }

        List<Integer> level;
        if (levels.size() < currentLevel) {
            level = new ArrayList<>();
            levels.add(level);
        } else {
            level = levels.get(currentLevel - 1);
        }
        level.add(node.val);
        levelOrder(node.left, currentLevel + 1, levels);
        levelOrder(node.right, currentLevel + 1, levels);
    }

}
