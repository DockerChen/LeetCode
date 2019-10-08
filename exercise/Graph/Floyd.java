package Graph;

public class Floyd {
    public static int[][] getMinDistance(int[][] matrix) {

        for (int k = 1; k < matrix.length; k++) {
            for (int i = 1; i < matrix.length; i++) {
                for (int j = 1; j < matrix.length; j++) {
                    if (matrix[i][k] < Integer.MAX_VALUE && matrix[k][j] < Integer.MAX_VALUE && matrix[i][j] > matrix[i][k] + matrix[k][j]) {
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                    }
                }

            }
        }
        return matrix;

    }

}
