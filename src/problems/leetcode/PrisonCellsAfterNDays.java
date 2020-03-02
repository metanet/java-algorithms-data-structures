package problems.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/prison-cells-after-n-days/
 */
public class PrisonCellsAfterNDays {

    public static void main(String[] args) {
        int[] cells = {0, 1, 0, 1, 1, 0, 0, 1};
        int N = 1000000;
        System.out.println(Arrays.toString(prisonAfterNDays(cells, N)));
    }

    public static int[] prisonAfterNDays(int[] cells, int N) {
        // state -> day
        Map<Integer, Integer> seen = new HashMap<>();

        // state = cells array is represented as an integer
        int state = 0;
        for (int i = 0; i < 8; ++i) {
            if (cells[i] > 0) {
                state ^= 1 << i;
            }
        }

        // While days remaining, simulate a day
        while (N > 0) {
            // If this is a cycle, fast forward by
            // seen.get(state) - N, the period of the cycle.
            long seenDay = seen.getOrDefault(state, -1);
            if (seenDay != -1) {
//                System.out.println("N: " + N + " seen day: " + seenDay);
                N %= seenDay - N;
//                System.out.println("_ N: " + N + " seen day: " + seenDay);
            }

            seen.put(state, N);

            if (N >= 1) {
                N--;
                state = nextDay(state);
            }
        }

        // Convert the state back to the required answer.
        Arrays.fill(cells, 0);
        for (int i = 0; i < 8; ++i) {
            if (((state >> i) & 1) > 0) {
                cells[i] = 1;
            }
        }

        return cells;
    }

    public static int nextDay(int state) {
        int nextDayState = 0;

        // We only loop from 1 to 6 because 0 and 7 are impossible,
        // as those cells only have one neighbor.
        for (int i = 1; i <= 6; ++i) {
            if (((state >> (i - 1)) & 1) == ((state >> (i + 1)) & 1)) {
                nextDayState ^= 1 << i;
            }
        }

        return nextDayState;
    }

}
