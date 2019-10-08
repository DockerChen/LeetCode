package Graph;

public class Dijkstra {
    public static int[] getMinDistance(int[][] matrix) {
        int len = matrix.length;
        int[] dis = new int[len];
        int min, flag = -1;
        boolean[] mark = new boolean[len];

        for (int i = 1; i < len; i++) {
            dis[i] = matrix[1][i];
        }
        mark[1] = true;

        for (int i = 1; i < len; i++) {
            min = Integer.MAX_VALUE;
            for (int j = 1; j < len; j++) {
                if (!mark[j] && dis[j] < min) {
                    min = dis[j];
                    flag = j;
                }
            }
            mark[flag] = true;

            for (int j = 1; j < len; j++) {
                if (matrix[flag][j] < Integer.MAX_VALUE && dis[flag] < Integer.MAX_VALUE && dis[j] > dis[flag] + matrix[flag][j]) {
                    dis[j] = dis[flag] + matrix[flag][j];
                }
            }
        }

        return dis;

    }
}
