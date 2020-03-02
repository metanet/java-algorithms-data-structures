package problems.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * https://leetcode.com/problems/daily-temperatures/
 */
public class DailyTemperatures {

    public static void main(String[] args) {
        int[] T = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = dailyTemperatures(T);
        System.out.println(Arrays.toString(result));
    }

    /**
     * T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be
     * [ 1,  1,  4,  2,  1,  1,  0,  0]
     * <p>
     * stack: 75, 71, 69
     */
    public static int[] dailyTemperatures(int[] T) {
        if (T == null || T.length == 0) {
            return new int[0];
        }

        Deque<Temperature> stack = new ArrayDeque<>();
        int[] result = new int[T.length];

        for (int i = 0; i < T.length; i++) {
            int t = T[i];

            while (stack.size() > 0 && stack.peekLast().temperature < t) {
                Temperature temperature = stack.removeLast();
                result[temperature.index] = i - temperature.index;
            }

            stack.addLast(new Temperature(i, t));
        }

        while (stack.size() > 0) {
            Temperature temperature = stack.removeLast();
            result[temperature.index] = 0;
        }

        return result;
    }

    private static class Temperature {
        final int index;
        final int temperature;

        Temperature(int index, int temperature) {
            this.index = index;
            this.temperature = temperature;
        }
    }

}
