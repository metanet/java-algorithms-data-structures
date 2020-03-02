package problems.leetcode;

import java.util.List;

/**
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 */
public class KthSmallestElementInBST {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

//    public int kthSmallest(TreeNode root, int k) {
//        List<Integer> visited = new ArrayList<>();
//        kthSmallest(root, k, visited);
//        return visited.get(k - 1);
//    }

    public int kthSmallest(TreeNode root, int k) {
        kthSmallest2(root, k);
        return found.val;
    }

    private void kthSmallest(TreeNode node, int k, List<Integer> visited) {
        if (node == null) {
            return;
        }

        kthSmallest(node.left, k, visited);
        visited.add(node.val);
        if (visited.size() < k) {
            kthSmallest(node.right, k, visited);
        }
    }

    private int count;
    private TreeNode found;

    private void kthSmallest2(TreeNode node, int k) {
        if (node == null) {
            return;
        }

        kthSmallest2(node.left, k);

        if (found == null && ++count == k) {
            found = node;
            return;
        }

        kthSmallest2(node.right, k);
    }

}
