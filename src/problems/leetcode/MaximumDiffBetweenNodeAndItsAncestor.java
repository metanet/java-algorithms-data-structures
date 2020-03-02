package problems.leetcode;

/**
 * https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/submissions/
 */
public class MaximumDiffBetweenNodeAndItsAncestor {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    private static class MinMax {
        final int min;
        final int max;

        MinMax(int val) {
            min = max = val;
        }

        MinMax(int min, int max) {
            this.min = min;
            this.max = max;
        }

        int maxAbsDiff(int val) {
            return Math.max(Math.abs(val - min), Math.abs(val - max));
        }

        MinMax getFor(int val) {
            if (val < min) {
                return new MinMax(val, max);
            } else if (val > max) {
                return new MinMax(min, val);
            } else {
                return this;
            }
        }
    }

    public int maxAncestorDiff(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return maxAncestorDiff(root, new MinMax(root.val));
    }

    int maxAncestorDiff(TreeNode node, MinMax minMax) {
        if (node == null) {
            return 0;
        }

        MinMax localMinMax = minMax.getFor(node.val);
        int leftDiff = maxAncestorDiff(node.left, localMinMax);
        int rightDiff = maxAncestorDiff(node.right, localMinMax);
        return Math.max(minMax.maxAbsDiff(node.val), Math.max(leftDiff, rightDiff));
    }

}
