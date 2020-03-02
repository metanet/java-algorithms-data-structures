package problems.leetcode;

import static java.lang.Math.max;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 * <p>
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/discuss/347053/DP-with-explaination-easy-to-understand
 * <p>
 * NOTICE:
 * 1.Each day we can have 3 operations : buy, sell or cooldown(do nothing)
 * 2.We can only sell after buy
 * 3.Must be a cooldown after sell
 * <p>
 * To sovle this problem with DP, we can give 2 definition :
 * 1.buy[i], means the max profit we can get if the status end with buy([buy,cooldown,cooldown] also means end with buy) at i-th day(i=0,1,2...)
 * 2.sell[i], means the max profit we can get if the status end with sell([sell,cooldown,cooldown] also means end with sell) at i-th day(i=0,1,2...)
 * (Understand end with buy(sell) is important)
 * <p>
 * Obviously, buy[i] >= buy[i-1] >= ...... buy[1] >=buy[0] and sell[i] >= sell[i-1] >= ...... sell[1] >=sell[0]
 * <p>
 * We don't need to define cooldown[i] which means the max profit we can get if status end with cooldown at i-th day(i=0,1,2...).Because if give any 2 variables of { buy, sell, cooldown } , and the last variable can be fixed.
 * <p>
 * To calculate buy[i]:
 * 1.If we choose to buy at i-th day, then buy[i] = sell[i-2]-prices[i]. Because the i-1-th day must be cooldown, and we spend prices[i] to buy.
 * 2.If we choose to cooldown at i-th day, then buy[i] = buy[i-1].Notice this also means end with buy.
 * So buy[i] = max(sell[i-2]-prices[i],buy[i-1])
 * <p>
 * To calculate sell[i]:
 * 1.If we choose to sell at i-th day, then sell[i] = buy[i-1]+prices[i].
 * 2.If we choose to cooldown at i-th day, then sell[i] = sell[i-1].
 * So sell[i] = max(buy[i-1]+prices[i],sell[i-1])
 * <p>
 * Finally, the max profit we can get is sell[len-1] (len = prices.size()). Because sell always after buy.
 */
public class BestTimeToBuyAndSellStockWithCooldown {

    static int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }

        int[] buy = new int[len], sell = new int[len];
        buy[0] = -prices[0];
        buy[1] = max(buy[0], -prices[1]);
        sell[1] = max(0, prices[1] - prices[0]);

        for (int i = 2; i < len; ++i) {
            buy[i] = max(sell[i - 2] - prices[i] /* buy on this day */, buy[i - 1] /* cooldown on this day */);
            sell[i] = max(buy[i - 1] + prices[i] /* sell on this day */, sell[i - 1] /* cooldown on this day */);
        }

        return sell[len - 1];
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{1, 2, 3, 0, 5}));
    }

}
