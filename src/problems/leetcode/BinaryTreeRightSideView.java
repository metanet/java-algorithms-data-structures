package problems.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-right-side-view/
 */
public class BinaryTreeRightSideView {

    private static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traverse(root, result, 1);
        return result;
    }

    private void traverse(TreeNode root, List<Integer> result, int level) {
        if (root == null) {
            return;
        }

        if (result.size() < level) {
            result.add(root.val);
        }

        traverse(root.right, result, level + 1);
        traverse(root.left, result, level + 1);
    }

}
