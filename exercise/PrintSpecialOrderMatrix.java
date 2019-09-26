public class PrintSpecialOrderMatrix {

    public static void rotataMatrix(int[][] arr) {
        if (arr == null) {
            return;
        }
        int sRow = 0;
        int sColumn = 0;
        int dRow = arr.length - 1;
        int dColumn = arr[0].length - 1;
        while (sRow <= dRow && sColumn <= dColumn) {
            printSpecialOrderMatrix(arr, sRow++, sColumn++, dRow--, dColumn--);
        }
    }

    public static void printSpecialOrderMatrix(int[][] arr, int sRow, int sColumn, int dRow, int dColumn) {
//        单行
        if (sRow == dRow) {
            for (int i = sColumn; i <= dColumn; i++) {
                System.out.print(arr[sRow][i] + " ");
            }
//       单列
        } else if (sColumn == dColumn) {
            for (int i = sRow; i <= dRow; i++) {
                System.out.print(arr[i][sColumn] + " ");
            }
//      其他情况
        } else {
            int curRow = sRow;
            int curColumn = sColumn;
            while (curColumn != dColumn) {
                System.out.print(arr[curRow][curColumn++] + " ");
            }
            while (curRow != dRow) {
                System.out.print(arr[curRow++][curColumn] + " ");
            }
            while (curColumn != sColumn) {
                System.out.print(arr[curRow][curColumn--] + " ");

            }
            while (curRow != sRow) {
                System.out.print(arr[curRow--][curColumn] + " ");

            }
        }
    }

    public static int[][] gengerateArray() {
        int[][] arr = new int[(int) (Math.random() * 10)][(int) (Math.random() * 10)];
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
    }

    public static void main(String[] args) {
        int[][] arr = gengerateArray();
        printMatrix(arr);
        System.out.println();
        rotataMatrix(arr);
    }

}
