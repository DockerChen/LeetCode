public class MaxValue {

    public static int process(int[] w, int[] v, int bag, int i, int bagRes) {
        if (bagRes > bag) {
            return Integer.MIN_VALUE;
        }
        if (i == w.length) {
            return 0;
        }

        return Math.max(v[i] + process(w, v, bag, i + 1, bagRes + w[i]),
                process(w, v, bag, i + 1, bagRes));

    }

    public static int processDP(int[] w, int[] v, int bag) {
        int[][] dp = new int[w.length + 1][bag + 1];
        for (int i = 0; i < bag + 1; i++) {
            dp[w.length][i] = 0;
        }

        for (int i = w.length - 1; i >= 0; i--) {
            for (int j = bag; j >= 0; j--) {
                if ((j + w[i]) > bag) {
                    dp[i][j] = dp[i + 1][j];
                } else {
                    dp[i][j] = Math.max(v[i] + dp[i + 1][j + w[i]], dp[i + 1][j]);
                }
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] w = { 3, 2, 4, 7 };
        int[] v = { 5, 6, 3, 19 };
        int bag = 11;
        System.out.println(process(w,v,bag,0,0));
        System.out.println(processDP(w,v,bag));

    }

}
