package problems.leetcode;

/**
 * https://leetcode.com/problems/monotonic-array/
 */
public class MonotonicArray {

    public boolean isMonotonic(int[] A) {
        // 0: equals
        // 1: increasing
        // 2: decrementing
        int state = 0;

        for (int i = 1; i < A.length; i++) {
            int curr = A[i], prev = A[i - 1];
            if (curr > prev) {
                if (state == 0) {
                    state = 1;
                } else if (state == 2) {
                    return false;
                }
            } else if (curr < prev) {
                if (state == 0) {
                    state = 2;
                } else if (state == 1) {
                    return false;
                }
            }
        }

        return true;
    }

}
