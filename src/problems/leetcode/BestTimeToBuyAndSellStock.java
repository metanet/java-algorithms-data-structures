package problems.leetcode;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 */
public class BestTimeToBuyAndSellStock {

    public static int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }

        int minPrice = prices[0], maxPrice = 0, maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                if (maxPrice == 0) {
                    minPrice = prices[i];
                } else {
                    maxProfit = Math.max(maxProfit, maxPrice - minPrice);
                    minPrice = prices[i];
                    maxPrice = 0;
                }
            } else if (prices[i] > maxPrice) {
                maxPrice = prices[i];
            }
        }

        return Math.max(maxProfit, maxPrice - minPrice);
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

}
