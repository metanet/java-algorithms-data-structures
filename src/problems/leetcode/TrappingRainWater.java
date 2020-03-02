package problems.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

import static java.lang.Math.min;

/**
 * https://leetcode.com/problems/trapping-rain-water/
 */
public class TrappingRainWater {

    public static void main(String[] args) {
        int[] heights = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
//        int[] heights = new int[]{2, 0, 2};
        System.out.println(trap(heights));
        System.out.println(trap2(heights));
    }

    public static int trap(int[] heights) {
        int n = heights.length;
        int[] maxHeightsLeft = new int[n], maxHeightsRight = new int[n];
        for (int i = 0; i < n; i++) {
            maxHeightsLeft[i] = Math.max(heights[i], i > 0 ? maxHeightsLeft[i - 1] : 0);
        }

        for (int i = n - 1; i >= 0; i--) {
            maxHeightsRight[i] = Math.max(heights[i], i < n - 1 ? maxHeightsRight[i + 1] : 0);
        }

        int totalWater = 0;
        for (int i = 0; i < n; i++) {
            int maxHeightLeft = i > 0 ? maxHeightsLeft[i - 1] : 0, maxHeightRight = i < n - 1 ? maxHeightsRight[i + 1] : 0;
            int water = Math.max(Math.min(maxHeightLeft, maxHeightRight) - heights[i], 0);
            totalWater += water;
        }

        return totalWater;
    }


    public static int trap2(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        Deque<Integer> stack = new ArrayDeque<>();
        int totalWater = 0;

        for (int i = 0; i < heights.length; i++) {
            int height = heights[i];
            while (stack.size() > 0 && heights[stack.peekLast()] < height) {
                int bottomHeight = heights[stack.removeLast()];

                if (stack.isEmpty()) {
                    break;
                }

                int topHeight = min(heights[stack.peekLast()], height);
                int distance = i - stack.peekLast() - 1;
                totalWater += (topHeight - bottomHeight) * distance;
            }

            stack.addLast(i);
        }

        return totalWater;
    }

}
