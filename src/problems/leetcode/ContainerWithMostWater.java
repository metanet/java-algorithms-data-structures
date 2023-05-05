package problems.leetcode;

import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/container-with-most-water/
 */
public class ContainerWithMostWater {

    // runtime: O(N)
    // space: O(1)
    public static int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }

        int i = 0;
        int j = height.length - 1;
        int hl = height[i];
        int hr = height[j];
        int maxArea = Math.min(hl, hr) * (j - i);

        while (i < j) {
            if (hl < hr) {
                while (i < j && hl >= height[i]) {
                    i++;
                }
                if (i < j) {
                    hl = height[i];
                }
            } else {
                while (i < j && hr >= height[j]) {
                    j--;
                }
                if (i < j) {
                    hr = height[j];
                }
            }

            if (i < j) {
                maxArea = Math.max(maxArea, Math.min(hl, hr) * (j - i));
            }
        }

        return maxArea;
    }

    public static int maxArea3(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int left = 0, right = height.length - 1, maxArea = 0;
        while (left <= right) {
            int width = right - left;
            int lowHeight;
            if (height[left] < height[right]) {
                lowHeight = height[left++];
            } else {
                lowHeight = height[right--];
            }
            int area = width * lowHeight;
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

    // brute force
    public static int maxArea2(int[] height) {
        int maxArea = 0, maxH = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] < maxH) {
                continue;
            }
            for (int j = i + 1; j < height.length; j++) {
                int w = j - i;
                int h = Math.min(height[i], height[j]);
                int area = w * h;
                if (area > maxArea) {
                    maxArea = area;
                    maxH = h;
                }
                // maxArea = Math.max(maxArea, w * h);
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println(maxArea(new int[] { 1, 8, 6, 2, 5, 4, 8, 3, 7 }));
        System.out.println(maxArea(new int[] { 1, 0, 0, 0, 0, 0, 0, 2, 2 }));
        System.out.println(maxArea(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }));
    }

}
