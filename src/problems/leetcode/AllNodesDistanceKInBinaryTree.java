package problems.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 */
public class AllNodesDistanceKInBinaryTree {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private List<Integer> result = new ArrayList<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        find(root, target.val, K);
        return result;
    }

    private int find(TreeNode node, int targetVal, int targetDistance) {
        if (node == null) {
            return 0;
        }

        if (node.val == targetVal) {
            if (targetDistance == 0) {
                result.add(node.val);
                return -1;
            }

            traverseUntilDepth(node.left, 1, targetDistance);
            traverseUntilDepth(node.right, 1, targetDistance);
            return 1;
        }

        int distance = find(node.left, targetVal, targetDistance);
        if (distance > 0) {
            if (distance == targetDistance) {
                result.add(node.val);
            }
            traverseUntilDepth(node.right, 1, targetDistance - distance);
        } else {
            distance = find(node.right, targetVal, targetDistance);
            if (distance > 0) {
                if (distance == targetDistance) {
                    result.add(node.val);
                }
                traverseUntilDepth(node.left, 1, targetDistance - distance);
            }
        }

        return distance > 0 ? distance + 1 : 0;
    }

    private void traverseUntilDepth(TreeNode node, int depth, int targetDepth) {
        if (node == null) {
            return;
        } else if (depth == targetDepth) {
            result.add(node.val);
            return;
        }

        traverseUntilDepth(node.left, depth + 1, targetDepth);
        traverseUntilDepth(node.right, depth + 1, targetDepth);
    }

    public static void main(String[] args) {
        TreeNode target = new TreeNode(5);
        TreeNode root2 = new TreeNode(15);
        TreeNode root = new TreeNode(3);
        root.left = target;
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        root2.right = root;
        root2.left = new TreeNode(20);

//        TreeNode root = new TreeNode(0);
//        root.left = new TreeNode(1);
//        root.left.right = new TreeNode(2);
//        root.left.right.right = new TreeNode(3);
//        root.left.right.right.right = new TreeNode(4);

        int K = 1;

        List<Integer> result = new AllNodesDistanceKInBinaryTree().distanceK(root, target, K);
        System.out.println(result);
    }

}
