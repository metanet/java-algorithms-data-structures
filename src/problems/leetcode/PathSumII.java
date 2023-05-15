package problems.leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/path-sum-ii
public class PathSumII {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        traverse(root, targetSum, new ArrayList<>(), result);
        return result;
    }

    private void traverse(TreeNode root, int targetSum, List<Integer> path, List<List<Integer>> result) {
        if (root == null) {
            return;
        }

        targetSum -= root.val;
        path.add(root.val);
        if (targetSum == 0 && root.left == null && root.right == null) {
            result.add(new ArrayList<>(path));
            path.remove(path.size() - 1);
            return;
        }

        traverse(root.left, targetSum, path, result);
        traverse(root.right, targetSum, path, result);
        path.remove(path.size() - 1);
    }

}
