package problems.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/coin-change/
 */
public class CoinChange {

    private Map<Integer, Integer> memo = new HashMap<>();

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        Integer memoized = memo.get(amount);
        if (memoized != null) {
            return memoized;
        }

        int minCoins = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (coin <= amount) {
                int coinCount = coinChange(coins, amount - coin);
                if (coinCount != -1 && minCoins > 1 + coinCount) {
                    minCoins = 1 + coinCount;
                }
            }
        }

        minCoins = minCoins != Integer.MAX_VALUE ? minCoins : -1;
        memo.put(amount, minCoins);
        return minCoins;
    }

    public int coinChangeDP(int[] coins, int targetAmount) {
        if (targetAmount == 0) {
            return 0;
        }

        int max = targetAmount + 1;
        int[] dp = new int[targetAmount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int amount = 1; amount <= targetAmount; amount++) {
            int coinCount = dp[amount];
            for (int coin : coins) {
                if (amount >= coin && coinCount > 1 + dp[amount - coin]) {
                    coinCount = 1 + dp[amount - coin];
                }
            }

            dp[amount] = coinCount;
        }

        return dp[targetAmount] == max ? -1 : dp[targetAmount];
    }

    public static void main(String[] args) {
        int[] coins = {4, 2, 5};
        int amount = 11;

        System.out.println(new CoinChange().coinChange(coins, amount));
        System.out.println(new CoinChange().coinChangeDP(coins, amount));
    }

}
