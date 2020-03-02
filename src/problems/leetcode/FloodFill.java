package problems.leetcode;

/**
 * https://leetcode.com/problems/flood-fill/
 */
public class FloodFill {

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        floodFill(image, image.length, image[0].length, sr, sc, image[sr][sc], newColor, new boolean[image.length][image[0].length]);
        return image;
    }

    private void floodFill(int[][] image, int h, int w, int i, int j, int expectedColor, int newColor, boolean[][] processed) {
        if (i < 0 || i >= h || j < 0 || j >= w || processed[i][j] || image[i][j] != expectedColor) {
            return;
        }

        image[i][j] = newColor;
        processed[i][j] = true;
        floodFill(image, h, w, i + 1, j, expectedColor, newColor, processed);
        floodFill(image, h, w, i - 1, j, expectedColor, newColor, processed);
        floodFill(image, h, w, i, j + 1, expectedColor, newColor, processed);
        floodFill(image, h, w, i, j - 1, expectedColor, newColor, processed);
    }

}
