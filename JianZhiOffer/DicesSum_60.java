import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DicesSum_60 {
    //动态规划
    public List<Map.Entry<Integer, Double>> dicesSum(int n) {
        List<Map.Entry<Integer, Double>> res = new ArrayList<>();
        // dp[i][j]表示前i个骰子产生点数j的次数
        long[][] dp = new long[n + 1][6 * n + 1];
        // 设置初始条件
        for (int i = 1; i <= 6; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 1 * i; j <= 6 * n; j++) {
                for (int k = 1; k <= 6 && k <= j; k++) {
                    dp[i][j] += dp[i - 1][j - k];
                }
            }
        }

        double totalNum = Math.pow(6, n);
        double p = 0;
        for (int i = n; i <= 6 * n; i++) {
            p = dp[n][i] / totalNum;
            p = Double.valueOf(String.format("%.2f", p));
            res.add(new AbstractMap.SimpleEntry<>(i, p));
        }
        return res;
    }

    //递归，时间复杂度较高
    public List<Map.Entry<Integer, Double>> dicesSumByDiGui(int n) {
        List<Map.Entry<Integer, Double>> res = new ArrayList<>();
        double totalNum = Math.pow(6, n);
        double p = 0;
        long count = 0;
        for (int i = n; i <= 6 * n; i++) {
            count = getCount(n, i);
            p = count / totalNum;
            // 保留两位小数
            p = Double.valueOf(String.format("%.2f", p));
            res.add(new AbstractMap.SimpleEntry<>(i, p));
        }
        return res;
    }

    public long getCount(int num, int sum) {
        if (num < 1 || sum > 6 * num || sum < num) {
            return 0;
        }
        if (num == 1) {
            return 1;
        }

        long count = getCount(num - 1, sum - 1) + getCount(num - 1, sum - 2)
                + getCount(num - 1, sum - 3) + getCount(num - 1, sum - 4)
                + getCount(num - 1, sum - 5) + getCount(num - 1, sum - 6);

        return count;

    }


    public static void main(String[] args) {
        DicesSum_60 test = new DicesSum_60();
        List<Map.Entry<Integer, Double>> res = test.dicesSum(5);
        for (Map.Entry<Integer, Double> map : res) {
            System.out.println(map.toString());

        }

    }


}
