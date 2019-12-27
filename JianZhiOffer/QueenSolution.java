import java.util.Arrays;

public class QueenSolution {
    //修改棋盘的大小，可模拟其他皇后类似问题
    //模拟一个8X8的棋盘，0代表没有放置，1代表放置了一个皇后
    private int[][] board = new int[8][8];

    //解法的数量
    private int total = 0;

    /**
     * 放置皇后的时候从第0行开始，依次放置一个
     * 如果放置成功，那么继续放置下一行。（0-7）
     * 当要放置k=8的时候说明已经全部放置完
     * 毕了，找到了一个对应的解
     *
     * @param k 放置第几个皇后，K从0开始
     */
    //放置第K个皇后
    public void putQueen(int k) {
        int max = board.length;
        //放置第8个，说明棋盘已经放置完毕了，输出结果。
        if (k >= max) {
            //找到一个解，打印出来。
            total++;
            System.out.println(String.format("=============%s===============", total));
            for (int i = 0; i < max; i++) {
                System.out.println(Arrays.toString(board[i]));
            }
            System.out.println("=============================");
        } else {

            for (int i = 0; i < max; i++) {
                if (check(k, i)) {
                    board[k][i] = 1;
                    putQueen(k + 1);
                    //回溯
                    board[k][i] = 0;
                }
            }
        }
    }

    /**
     * 皇后放置的时候是从上到下每一行放置的，所以不用检查改行以及之后的行
     * 所以只用检查列以及左上右上对角线
     *
     * @param row 检查的对应行
     * @param col 检查的对应列
     * @return 返回改点是否满足可以放置一个皇后
     */
    private boolean check(int row, int col) {
        //检查列是否有皇后
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) {
                return false;
            }
        }

        //检查左上对角线是否有皇后
        for (int m = row - 1, n = col - 1; m >= 0 && n >= 0; m--, n--) {
            if (board[m][n] == 1) {
                return false;
            }
        }

        //检查右上对角线是否有皇后
        for (int m = row - 1, n = col + 1; m >= 0 && n < board[0].length; m--, n++) {
            if (board[m][n] == 1) {
                return false;
            }
        }
        return true;

    }

    public static void main(String[] args) {
        QueenSolution solution = new QueenSolution();
        solution.putQueen(0);
    }
}
