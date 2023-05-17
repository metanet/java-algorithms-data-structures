package problems.leetcode;

// https://leetcode.com/problems/sum-root-to-leaf-numbers/
public class SumRootToLeafNumbers {
    
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

    private int sum;

    // runtime: O(N)
    // space: O(H)
    public int sumNumbers(TreeNode root) {
        sumNumbers(root, 0);
        return sum;
    }

    private void sumNumbers(TreeNode root, int parent) {
        if (root == null) {
            return;
        } 
        
        int num = parent * 10 + root.val;
        if (root.left == null && root.right == null) {
            sum += num;   
        }

        sumNumbers(root.left, num);
        sumNumbers(root.right, num);
    }
}
