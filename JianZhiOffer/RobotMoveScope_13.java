public class RobotMoveScope_13 {
    public int movingCount(int threshold, int rows, int cols) {
        //标记数组
        boolean[][] mark = new boolean[rows][cols];
        //存储每个位置的数位和
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = getValue(i) + getValue(j);
            }
        }

        return process(threshold, matrix, mark, 0, 0, rows, cols);

    }
    private int process(int threshold, int[][] matrix, boolean[][] mark, int i, int j, int rows, int cols) {
        int count = 0;
        //递归终止条件
        if (i < 0 || j < 0 || i >= rows || j >= cols || matrix[i][j] > threshold || mark[i][j]) {
            return 0;
        }
        //将访问过的位置标记为true
        mark[i][j] = true;
        //访问当前位置，加1，然后继续遍历该位置傍边的位置，累加起来，最终的返回值就是所能到达的格子数
        count = 1 + process(threshold, matrix, mark, i - 1, j, rows, cols) + process(threshold, matrix, mark, i + 1, j, rows, cols) +
                process(threshold, matrix, mark, i, j - 1, rows, cols) + process(threshold, matrix, mark, i, j + 1, rows, cols);
        return count;

    }

    //计算一个整数的数位之和
    public int getValue(int num) {
        int res = 0;
        int tmp = 0;
        while (num / 10 > 0) {
            tmp = num / 10;
            res += num - tmp * 10;
            num = tmp;
        }
        res += num;
        return res;

    }

    public static void main(String[] args) {
        RobotMoveScope_13 robotMoveScope_13 = new RobotMoveScope_13();
        int res = robotMoveScope_13.movingCount(3, 3, 3);
        System.out.println(res);
    }
}
