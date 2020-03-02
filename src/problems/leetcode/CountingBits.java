package problems.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/counting-bits/
 */
public class CountingBits {

    public static void main(String[] args) {
        CountingBits sol = new CountingBits();
        int[] bitCounts = sol.countBits(15);
        System.out.println(Arrays.toString(bitCounts));
    }

    public int[] countBits(int num) {
        int[] counts = new int[num + 1];
        counts[0] = 0;
        int lastPow2 = 0;
        for (int i = 1; i <= num; i++) {
            if (isPow2(i)) {
                counts[i] = 1;
                lastPow2 = i;
            } else {
                counts[i] = counts[i - lastPow2] + 1;
            }
        }
        return counts;
    }

    public boolean isPow2(int i) {
        return (i & i - 1) == 0;
    }

}
