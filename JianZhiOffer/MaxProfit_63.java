public class MaxProfit_63 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        // min表示前i只股票价格的最小值，maxProfile表示前i只股票获得的最大利润
        int min = prices[0];
        int maxProfit = prices[1] - prices[0];
        for (int i = 1; i < prices.length; i++) {
            maxProfit = maxProfit > prices[i] - min ? maxProfit : prices[i] - min;
            if (prices[i] < min) {
                min = prices[i];
            }
        }

        if (maxProfit < 0) {
            return 0;
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        MaxProfit_63 test = new MaxProfit_63();
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(test.maxProfit(prices));
    }
}
