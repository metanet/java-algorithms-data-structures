package problems.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/find-duplicate-subtrees/
 */
public class FindDuplicateSubtrees {

    static private class TreeNode {
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

    Map<List<Integer>, TreeNode> duplicates = new HashMap<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        findDuplicateSubtrees(root, new ArrayList<>(), -3, new HashSet<>());
        return new ArrayList<>(duplicates.values());
    }

    private void findDuplicateSubtrees(TreeNode node, List<Integer> parent, int marker, Set<List<Integer>> subtrees) {
        if (node == null) {
            parent.add(marker);
            parent.add(-4);
            System.out.println("null node");
            return;
        }

        System.out.println("node: " + node);

        List<Integer> sub = new ArrayList<>();
        findDuplicateSubtrees(node.left, sub, -1, subtrees);
//        sub.add(marker);
        sub.add(node.val);
        findDuplicateSubtrees(node.right, sub, -2, subtrees);

        if (!subtrees.add(sub)) {
            System.out.println("duplicate node: " + node + " sub: " + sub + " subtrees: " + subtrees);
            duplicates.put(sub, node);
        } else {
            System.out.println("added subtree: " + sub + " for " + node);
        }

        parent.addAll(sub);
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode("n1", 0);
        TreeNode n2 = new TreeNode("n2", 0);
        TreeNode n3 = new TreeNode("n3", 0);
        TreeNode n4 = new TreeNode("n4", 0);
        TreeNode n5 = new TreeNode("n5", 0);
        TreeNode n6 = new TreeNode("n6", 0);

        n1.left = n2;
        n1.right = n3;

        n2.left = n4;
        n3.right = n5;
        n5.right = n6;

        List<TreeNode> duplicates = new FindDuplicateSubtrees().findDuplicateSubtrees(n1);
    }

}
