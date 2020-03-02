package problems.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 */
public class LargestRectangleInHistogram {

    public static void main(String[] args) {
        /*
                 x
               x x
               x x
               x x   x
           x   x x x x
           x x x x x x
         */
        int[] heights = {2, 1, 5, 6, 2, 3};

        /*
                  x
            x   x x
        x x x x x x x
        x x x x x x x x x
        x x x x x x x x x
         */

        int[] heights2 = {3, 3, 4, 3, 4, 5, 3, 2, 2};

        int largest = largestRectangleArea(heights2);
        System.out.println(largest);
    }

    public static int largestRectangleArea(int[] heights) {
        // {x, h}
        Deque<int[]> stack = new ArrayDeque<>();
        int maxArea = 0, n = heights.length;
        for (int i = 0; i < n; i++) {
            int h = heights[i];

            // push to the stack as the height increases
            if (stack.isEmpty() || stack.peekLast()[1] <= h) {
                stack.addLast(new int[]{i, h});
                continue;
            }

            // pop all the heights which are greater than the current height
            int leftMostX = -1;
            while (stack.size() > 0 && stack.peekLast()[1] > h) {
                int[] bar = stack.removeLast();
                int area = bar[1] * (i - bar[0]);
                maxArea = Math.max(maxArea, area);
                leftMostX = bar[0];
            }

            stack.addLast(new int[]{leftMostX, h});
            stack.addLast(new int[]{i, h});
        }

        while (stack.size() > 0) {
            int[] bar = stack.removeLast();
            int area = bar[1] * (n - bar[0]);
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

    /*
              x
        x   x x
    x x x x x x x
    x x x x x x x x x
    x x x x x x x x x



      x x
      x x x
    x x x x x
    x x x x x
    1 2 3 4

    stack: <1,3> <2,4> <3,3> <4,2>


     */

    public int largestRectangleArea2(int[] h) {
        if (h == null || h.length == 0) {
            return 0;
        } else if (h.length == 1) {
            return h[0];
        }

        // stack holds an array of size 2:
        //  [0] = left-most index of this area
        //  [1] = height of this area
        Deque<int[]> computing = new ArrayDeque<>();
        int maxArea = 0;
        computing.addLast(new int[]{0, h[0]});

        for (int i = 1; i < h.length; ++i) {
            final int prevH = computing.peek()[1];

            if (h[i] > prevH) {
                // start a new area
                computing.addLast(new int[]{i, h[i]});
            } else if (h[i] < prevH) {
                //  keep popping off stack until current
                //  h is greater than prevH or we empty
                //  stack

                // also compute area when you pop off stack
                int lastIndex = i;
                do {
                    final int[] leftAndH = computing.removeLast();
                    final int area = leftAndH[1] * (i - leftAndH[0]);
                    lastIndex = leftAndH[0];
                    if (area > maxArea) {
                        maxArea = area;
                    }
                } while (!computing.isEmpty() && h[i] < computing.peekLast()[1]);

                computing.addLast(new int[]{lastIndex, h[i]});
            }
        }

        // empty anything left of stack
        while (!computing.isEmpty()) {
            final int[] leftAndH = computing.removeLast();
            final int area = leftAndH[1] * (h.length - leftAndH[0]);
            if (area > maxArea) {
                maxArea = area;
            }
        }

        return maxArea;
    }

}
