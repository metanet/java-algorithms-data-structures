package problems.leetcode;

public class BinarySearch {

    private static int binarySearch(int[] vals, int val) {
        int l = 0, h = vals.length - 1;

        while (l <= h) {
            int m = (l + h) / 2;
            if (vals[m] == val) {
                return m;
            } else if (vals[m] < val) {
                l = m + 1;
            } else {
                h = m - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(binarySearch(new int[] { 1, 2, 3, 4, 5 }, 3));
        System.out.println(binarySearch(new int[] { 1, 2, 3, 4, 5 }, 5));
        System.out.println(binarySearch(new int[] { 1, 2, 3, 4, 5 }, 1));
        System.out.println(binarySearch(new int[] { 1, 2, 3, 4, 5 }, 6));
        System.out.println(binarySearch(new int[] { 1, 2, 3, 4, 5 }, -5));
    }

}
