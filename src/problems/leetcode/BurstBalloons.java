package problems.leetcode;

/**
 * https://leetcode.com/problems/burst-balloons/
 */
public class BurstBalloons {

    public static void main(String[] args) {
        int[] balloons = {3, 1, 5, 8};
        System.out.println(maxCoins(balloons));
    }

    public static int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        }

        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1, maxCoins = 0;
                // processing subarray: [i, j]
                for (int k = i; k <= j; k++) {
                    // nums[k] is burst last...
                    int leftCoins = k == 0 ? 0 : dp[i][k - 1];
                    int rightCoins = k == j ? 0 : dp[k + 1][j];
                    int leftBalloon = i == 0 ? 1 : nums[i - 1];
                    int rightBalloon = j == n - 1 ? 1 : nums[j + 1];
                    int coins = leftCoins + rightCoins + leftBalloon * nums[k] * rightBalloon;
//                    System.out.println("i: " + i + ", j: " + j + ", k: " + k + ", coins: " + coins);
                    maxCoins = Math.max(maxCoins, coins);
                }
                dp[i][j] = maxCoins;
            }
        }

        return dp[0][n - 1];
    }

}
