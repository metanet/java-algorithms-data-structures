package problems.leetcode;

/**
 * https://leetcode.com/problems/first-bad-version/
 */
public class FirstBadVersion {

    public static int BAD_VERSION = -1;

    public static int firstBadVersion(int n) {
        int l = 1, r = n;
        while (l < r) {
            int m = l + (r - l) / 2;

            if (isBadVersion(m)) {
                // go left
                r = m;
            } else {
                // go right
                l = m + 1;
            }
        }

        return l;
    }

    private static boolean isBadVersion(int version) {
        return version >= BAD_VERSION;
    }

    public static void main(String[] args) {
        BAD_VERSION = 3;
        int n = 20;
        System.out.println(firstBadVersion(n));
    }

}
