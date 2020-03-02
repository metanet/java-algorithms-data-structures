package problems.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 */
public class CountOfSmallerNumbersAfterSelf {

    private static final class Node {
        private int val;
        private int leftCount = 0, duplicateCount = 1;
        private Node left, right;

        Node(int val) {
            this.val = val;
        }

        int insert(int valToInsert) {
            if (valToInsert == val) {
                ++duplicateCount;
                return leftCount;
            } else if (valToInsert < val) {
                ++leftCount;
                if (left == null) {
                    left = new Node(valToInsert);
                    return 0;
                } else {
                    return left.insert(valToInsert);
                }
            } else if (right == null) {
                right = new Node(valToInsert);
                return leftCount + duplicateCount;
            } else {
                return leftCount + duplicateCount + right.insert(valToInsert);
            }
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", leftCount=" + leftCount +
                    ", duplicateCount=" + duplicateCount +
                    '}';
        }
    }

    public static List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length < 1) {
            return Collections.emptyList();
        }

        int n = nums.length;
        List<Integer> counts = new ArrayList<>(n);
        for (int i = 0; i < n - 1; ++i) {
            counts.add(null);
        }

        counts.add(0);

        Node root = new Node(nums[n - 1]);
        for (int i = n - 2; i >= 0; --i) {
            counts.set(i, root.insert(nums[i]));
        }

        return counts;
    }

    public static void main(String[] args) {
        int[] nums = {5, 2, 6, 1};
        List<Integer> counts = countSmaller(nums);
        System.out.println(counts);
    }

    /*
       Input : [5,2,6,1]
       Output: [2,1,1,0]

       <1,3>,<2,1>,<5,0>,<6,2>

       0 1 2 3 4 5
       5,2,2,5,6,1

       <1,5>, <2,2>, <2,1>, <5, 3>, <5, 0>, <6, 4>



     */

}
