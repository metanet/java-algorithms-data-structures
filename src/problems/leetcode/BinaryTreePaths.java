package problems.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-paths/
 */
public class BinaryTreePaths {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.left.left = new TreeNode(4);
//        root.left.right = new TreeNode(5);
//        root.right = new TreeNode(3);

        List<String> paths = new BinaryTreePaths().binaryTreePaths(root);
        for (String path : paths) {
            System.out.println(path);
        }
    }

    private List<String> allPaths = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        if (root != null) {
            binaryTreePaths(root, new StringBuilder());
        }

        return allPaths;
    }

    private void binaryTreePaths(TreeNode node, StringBuilder path) {
        int len = path.length();
        if (path.length() > 0) {
            path.append("->");
        }

        String str = String.valueOf(node.val);
        path.append(str);
        boolean leaf = true;
        if (node.left != null) {
            leaf = false;
            binaryTreePaths(node.left, path);
        }

        if (node.right != null) {
            leaf = false;
            binaryTreePaths(node.right, path);
        }

        if (leaf) {
            allPaths.add(path.toString());
        }

        path.setLength(len);
    }
}
