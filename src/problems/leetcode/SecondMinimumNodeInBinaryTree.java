package problems.leetcode;

/**
 * https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/
 */
public class SecondMinimumNodeInBinaryTree {

    private static class TreeNode {
        String name;
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(String name, int x) {
            this.name = name;
            val = x;
        }

        @Override
        public String toString() {
            return "TreeNode{" + "name='" + name + '\'' + ", val=" + val + '}';
        }
    }

    public static int findSecondMinimumValue(TreeNode node) {
        int v = findSecondMinimumValue2(node);
        if (v != -1 && v == node.val) {
            v = -1;
        }
        return v;
    }

    public static int findSecondMinimumValue2(TreeNode node) {
        if (node == null || (node.left == null && node.right == null)) {
            return -1;
        }

        assert node.left != null;
        assert node.right != null;

        if (node.left.left == null && node.left.right == null && node.right.left == null && node.right.right == null) {
            return Math.max(node.left.val, node.right.val);
        }

        if (node.left.val == node.right.val) {
            int left = (node.left.left != null && node.left.right != null) ? findSecondMinimumValue2(node.left) : node.left.val;
            int right = (node.right.left != null && node.right.right != null) ? findSecondMinimumValue2(node.right) : node.right.val;
            int r;
            if (left == node.val) {
                r = right;
            } else if (right == node.val) {
                r = left;
            } else {
                r = Math.min(left, right);
            }
            return r;
        } else if (node.left.val < node.right.val) {
            int left = (node.left.left != null && node.left.right != null) ? findSecondMinimumValue2(node.left) : node.left.val;
            int r;
            if (left == node.val) {
                r = node.right.val;
            } else {
                r = Math.min(left, node.right.val);
            }
            return r;
        } else {
            int right = (node.right.left != null && node.right.right != null) ? findSecondMinimumValue2(node.right) : node.right.val;
            int r;
            if (right == node.val) {
                r = node.left.val;
            } else {
                r = Math.min(node.left.val, right);
            }
            return r;
        }
    }

    public static void main2(String[] args) {
        TreeNode n1 = new TreeNode("n1", 1);
        TreeNode n2 = new TreeNode("n2", 1);
        TreeNode n3 = new TreeNode("n3", 3);
        n1.left = n2;
        n1.right = n3;

        TreeNode n4 = new TreeNode("n4", 1);
        TreeNode n5 = new TreeNode("n5", 1);
        n2.left = n4;
        n2.right = n5;

        TreeNode n6 = new TreeNode("n6", 3);
        TreeNode n7 = new TreeNode("n7", 4);
        n3.left = n6;
        n3.right = n7;

        TreeNode n8 = new TreeNode("n8", 3);
        TreeNode n9 = new TreeNode("n9", 1);
        n4.left = n8;
        n4.right = n9;

        TreeNode n10 = new TreeNode("n10", 1);
        TreeNode n11 = new TreeNode("n11", 1);
        n5.left = n10;
        n5.right = n11;

        TreeNode n12 = new TreeNode("n12", 3);
        TreeNode n13 = new TreeNode("n13", 8);
        n6.left = n12;
        n6.right = n13;

        TreeNode n14 = new TreeNode("n14", 4);
        TreeNode n15 = new TreeNode("n15", 8);
        n7.left = n14;
        n7.right = n15;

        TreeNode n16 = new TreeNode("n16", 3);
        TreeNode n17 = new TreeNode("n17", 3);
        n8.left = n16;
        n8.right = n17;

        TreeNode n18 = new TreeNode("n18", 1);
        TreeNode n19 = new TreeNode("n19", 6);
        n9.left = n18;
        n9.right = n19;

        TreeNode n20 = new TreeNode("n20", 2);
        TreeNode n21 = new TreeNode("n21", 1);
        n10.left = n20;
        n10.right = n21;

        System.out.println(findSecondMinimumValue(n1));
    }

    public static void main3(String[] args) {
        TreeNode n1 = new TreeNode("n1", 2);
        TreeNode n2 = new TreeNode("n2", 2);
        TreeNode n3 = new TreeNode("n3", 5);
        n1.left = n2;
        n1.right = n3;


        TreeNode n6 = new TreeNode("n6", 5);
        TreeNode n7 = new TreeNode("n7", 7);
        n3.left = n6;
        n3.right = n7;

        System.out.println(findSecondMinimumValue(n1));
    }


    public static void main(String[] args) {
        TreeNode n1 = new TreeNode("n1", 2);
        TreeNode n2 = new TreeNode("n2", 2);
        TreeNode n3 = new TreeNode("n3", 2);
        n1.left = n2;
        n1.right = n3;

//        TreeNode n6 = new TreeNode("n6", 2);
//        TreeNode n7 = new TreeNode("n6", 2);
//        n3.left = n6;
//        n3.right = n7;

        System.out.println(findSecondMinimumValue(n1));
    }

}
