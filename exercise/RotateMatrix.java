import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

import java.sql.PseudoColumnUsage;

public class RotateMatrix {

    public static void rotateMatrix(int[][] arr) {
        int sRow = 0;
        int sColumn = 0;
        int dRow = arr.length - 1;
        int dColumn = arr[0].length - 1;
        while (sRow < dRow) {
            printRotateMatrix(arr, sRow++, sColumn++, dRow--, dColumn--);
        }

    }

    public static void printRotateMatrix(int[][] arr, int sRow, int sColumn, int dRow, int dColumn) {
        int times = dColumn - sColumn;
        int tmp = 0;
        for (int i = 0; i < times; i++) {
            tmp = arr[sRow][sColumn + i];
            arr[sRow][sColumn + i] = arr[dRow - i][sColumn];
            arr[dRow - i][sColumn] = arr[dRow][dColumn - i];
            arr[dRow][dColumn - i] = arr[sRow + i][dColumn];
            arr[sRow + i][dColumn] = tmp;
        }


    }

    public static int[][] gengerateArray() {
        int[][] arr;
        //len: 1-11
        int len = (int) (Math.random() * 10 + 1);
        arr = new int[len][len];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = (int) (Math.random() * 10);
            }

        }
        return arr;

    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

    }


    public static void main(String[] args) {
        int[][] arr = gengerateArray();
        printMatrix(arr);
        rotateMatrix(arr);
        printMatrix(arr);

    }
}
