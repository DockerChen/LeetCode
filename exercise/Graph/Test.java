package Graph;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        int[][] matrix;
        int n, m, u, v, weight;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        matrix = new int[n + 1][n + 1];
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix.length; j++) {
                if (i == j) {
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = Integer.MAX_VALUE;

                }
            }
        }

        while (m-- > 0) {
            u = scanner.nextInt();
            v = scanner.nextInt();
            weight = scanner.nextInt();
            matrix[u][v] = weight;
        }

//        System.out.println("---Floyd---");
//
//        printMatrix(matrix);
//
//        matrix = Floyd.getMinDistance(matrix);
//        System.out.println();
//
//        printMatrix(matrix);

        System.out.println("---Dijkstra---");

        printMatrix(matrix);

        int[] dis = Dijkstra.getMinDistance(matrix);
        System.out.println();
        for (int i = 1; i < dis.length; i++) {
            System.out.print(dis[i] + " ");
        }

    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}
