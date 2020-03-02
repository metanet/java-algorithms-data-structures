package problems.leetcode;

/**
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 */
public class SerializeBinaryTree {

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

    public static String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    private static void serialize(TreeNode node, StringBuilder sb) {
        append(sb, node);

        if (node != null) {
            serialize(node.left, sb);
            serialize(node.right, sb);
        }
    }

    private static void append(StringBuilder sb, TreeNode node) {
        if (sb.length() > 0) {
            sb.append(' ');
        }

        if (node == null) {
            sb.append('n');
        } else {
            sb.append(node.val);
        }
    }

    public static TreeNode deserialize(String str) {
        return deserialize(new Deserializer(str));
    }

    private static TreeNode deserialize(Deserializer deserializer) {
        Integer val = deserializer.nextVal();
        if (val == null) {
            return null;
        }

        TreeNode node = new TreeNode(val);
        node.left = deserialize(deserializer);
        node.right = deserialize(deserializer);
        return node;
    }

    private static class Deserializer {
        String s;
        int i;

        Deserializer(String s) {
            this.s = s;
        }

        Integer nextVal() {
            int j = s.indexOf(' ', i);
            String token = s.substring(i, j != -1 ? j : s.length());
            i = j + 1;
            return token.equals("n") ? null : Integer.parseInt(token);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        //        root.right.right = new TreeNode(5);

        String s = serialize(root);
        System.out.println(s);

        TreeNode root2 = deserialize(s);
        System.out.println();
    }

}
