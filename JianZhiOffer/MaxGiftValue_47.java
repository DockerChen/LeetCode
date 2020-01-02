public class MaxGiftValue_47 {
    //递归
    public int getMost(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return 0;
        }
        return process(board, 0, 0);
    }

    public int process(int[][] board, int i, int j) {
        int res = board[i][j];
        if (i == board.length - 1 && j == board[0].length - 1) {
            return res;
        }

        if (i == board.length - 1) {
            return res + process(board, i, j + 1);
        }
        if (j == board[0].length - 1) {
            return res + process(board, i + 1, j);
        }

        return res + Math.max(process(board, i + 1, j), process(board, i, j + 1));
    }

    //动态规划（DP）
    public int getMostByDP(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return 0;
        }
        int rows = board.length;
        int columns = board[0].length;
        int[][] dp = new int[rows][columns];
        dp[0][0] = board[0][0];

        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + board[i][0];
        }
        for (int j = 1; j < columns; j++) {
            dp[0][j] = dp[0][j - 1] + board[0][j];
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + board[i][j];
            }
        }

        return dp[rows - 1][columns - 1];


    }
}
