package problems.leetcode;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 */
public class BestTimeToBuyAndSellStockII {

    public static int maxProfit(int[] prices) {
        if (prices == null) {
            return 0;
        }

        int totalProfit = 0, buyPrice = -1, len = prices.length;
        for (int i = 0; i < len; i++) {
            if (buyPrice == -1) {
                if (i < len - 1 && prices[i] < prices[i + 1]) {
                    buyPrice = prices[i];
                }
            } else {
                if (i == len - 1 || prices[i] > prices[i + 1]) {
                    totalProfit += prices[i] - buyPrice;
                    buyPrice = -1;
                }
            }
        }

        return totalProfit;
    }

    public static void main(String[] args) {
//        int[] prices = {7, 1, 5, 3, 6, 4};
        int[] prices = {1, 2, 3, 4, 5};
        System.out.println(maxProfit(prices));
    }

}
