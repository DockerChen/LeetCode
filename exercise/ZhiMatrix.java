public class ZhiMatrix {

    public static void zhiMatrix(int[][] arr) {
        int aRow = 0;
        int aColumn = 0;
        int bRow = 0;
        int bColumn = 0;
        int endRow = arr.length - 1;
        int endColumn = arr[0].length - 1;
        boolean flag = true;  //从下往上打印为true
        while (aRow != endRow + 1) {
            printZhiMatrix(arr, aRow, aColumn, bRow, bColumn, flag);
            if (aColumn == endColumn) {
                aRow++;
            } else {
                aColumn++;
            }

            if (bRow == endRow) {
                bColumn++;
            } else {
                bRow++;
            }
            flag = !flag;
        }

    }

    public static void printZhiMatrix(int[][] arr, int aRow, int aColumn, int bRow, int bColumn, boolean flag) {
//       从下往上
        if (flag) {
            for (int i = bRow; i >= aRow; i--) {
                System.out.print(arr[i][bColumn + (bRow - i)] + " ");

            }
            System.out.println();
//      从上往下
        } else {
            for (int i = aRow; i <= bRow; i++) {
                System.out.print(arr[i][aColumn - (i - aRow)] + " ");
            }
            System.out.println();
        }


    }

    public static int[][] gengerateArray() {
        int[][] arr;
        //1-11
        arr = new int[(int) (Math.random() * 10 + 1)][(int) (Math.random() * 10 + 1)];

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
        int[][] arr=gengerateArray();
        printMatrix(arr);
        zhiMatrix(arr);


    }
}
