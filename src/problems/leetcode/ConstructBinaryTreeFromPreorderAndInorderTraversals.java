package problems.leetcode;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversals {

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

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, inorder, 0, 0, preorder.length, inorder.length);
    }

    static TreeNode buildTree(int[] preorder, int[] inorder, int startP, int startI, int endP, int endI) {
        if (startP == endP) {
            return null;
        }

        TreeNode node = new TreeNode(preorder[startP]);

        int diff = getDiff(preorder[startP], inorder, startI, endI);
        if (diff > 0) {
            node.left = buildTree(preorder, inorder, startP + 1, startI, startP + 1 + diff, startI + diff);
        }
        node.right = buildTree(preorder, inorder, startP + diff + 1, startI + diff + 1, endP, endI);

        return node;
    }

    private static int getDiff(int node, int[] inorder, int startI, int endI) {
        for (int i = startI; i < endI; i++) {
            if (node == inorder[i]) {
                return i - startI;
            }
        }

        throw new IllegalArgumentException();
    }

    public static void main(String[] args) {
        //        TreeNode root = buildTree(new int[]{1, 3}, new int[]{1, 3});
        //        TreeNode root = buildTree(new int[]{1, 2}, new int[]{2, 1});
        //        TreeNode root = buildTree(new int[]{1, 2, 3}, new int[]{2, 1, 3});
        //        TreeNode root = buildTree(new int[]{1, 2, 3}, new int[]{3, 2, 1});
        //        TreeNode root = buildTree(new int[]{1, 2, 3, 4}, new int[]{3, 2, 4, 1});
        //        TreeNode root = buildTree(new int[]{1, 2, 3}, new int[]{2, 3, 1});
        //        TreeNode root = buildTree(new int[]{1, 2, 3}, new int[]{1, 3, 2});
        //        TreeNode root = buildTree(new int[]{1, 2, 3}, new int[]{1, 2, 3});
        //        TreeNode root = buildTree(new int[]{1, 2, 3, 4}, new int[]{1, 3, 2, 4});
        TreeNode root = buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        System.out.println();
    }
}
