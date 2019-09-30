public class MinPath {

    public static int getMinPath(int[][] arr, int i, int j) {
        int res = arr[i][j];
        if (i == arr.length - 1 && j == arr[0].length - 1) {
            return res;
        }
        if (i == arr.length - 1) {
            return res + getMinPath(arr, i, j + 1);
        }
        if (j == arr[0].length - 1) {
            return res + getMinPath(arr, i + 1, j);
        }
        return res + Math.min(getMinPath(arr, i, j + 1), getMinPath(arr, i + 1, j));

    }

    public static int getMinPathDP(int[][] arr) {
        int[][] dp = new int[arr.length][arr[0].length];
        dp[arr.length - 1][arr[0].length - 1] = arr[arr.length - 1][arr[0].length - 1];
        for (int k = arr[0].length - 2; k >= 0; k--) {
            dp[arr.length - 1][k] = arr[arr.length - 1][k] + dp[arr.length - 1][k + 1];
        }

        for (int k = arr.length - 2; k >= 0; k--) {
            dp[k][arr[0].length - 1] = arr[k][arr[0].length - 1] + dp[k + 1][arr[0].length - 1];
        }

        for (int k = arr.length - 2; k >= 0; k--) {
            for (int l = arr[0].length - 2; l >= 0; l--) {
                dp[k][l] = arr[k][l] + Math.min(dp[k + 1][l], dp[k][l + 1]);
            }
        }

        return dp[0][0];

    }

    public static void main(String[] args) {
        int[][] arr = {{1, 3, 5, 9}, {8, 1, 3, 4}, {5, 0, 6, 1}, {8, 8, 4, 0}};
        System.out.println(getMinPath(arr, 0, 0));
        System.out.println(getMinPathDP(arr));

    }
}
