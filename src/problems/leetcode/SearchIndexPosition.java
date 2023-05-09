package problems.leetcode;

// https://leetcode.com/problems/search-insert-position/
public class SearchIndexPosition {

    public static void main(String[] args) {
        System.out.println(searchInsert(new int[] { 1, 3, 5, 6 }, 1));
        System.out.println(searchInsert(new int[] { 1, 3, 5, 6 }, 3));
        System.out.println(searchInsert(new int[] { 1, 3, 5, 6 }, 2));
        System.out.println(searchInsert(new int[] { 1, 3, 5, 6 }, 8));
        System.out.println(searchInsert(new int[] { 1, 3, 5, 6 }, 0));
    }

    // runtime: O(lgN)
    // space: O(1)
    public static int searchInsert(int[] nums, int target) {
        // [1,3,5,6]
        // t = 2
        // 0 1 2 3
        // l r
        // m
        // r
        // m
        // l

        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (nums[m] == target) {
                return m;
            } else if (nums[m] < target) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        return l;
    }

}
