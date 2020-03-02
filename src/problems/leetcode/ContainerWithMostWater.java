package problems.leetcode;

/**
 * https://leetcode.com/problems/container-with-most-water/
 */
public class ContainerWithMostWater {

    public static int maxArea(int[] height) {
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
                //maxArea = Math.max(maxArea, w * h);
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(heights));
    }

}
