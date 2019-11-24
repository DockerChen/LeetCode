public class ClockwisePrintMatrix_29 {
    public static void clockwisePrintMatrix(int[][] arr) {
        if (arr == null) {
            return;
        }

        print(arr, 0, 0, arr.length - 1, arr[0].length - 1);

    }

    public static void print(int[][] arr, int leftX, int leftY, int rightX, int rightY) {
        //递归终止条件
        if (leftX > rightX || leftY > rightY) {
            return;
        }
        //单行和单列需要单独处理，否则会输出重复的序列
        if (leftX == rightX) {
            for (int i = leftY; i <= rightY; i++) {
                System.out.print(arr[leftX][i] + " ");
            }
        } else if (leftY == rightY) {
            for (int i = leftX; i <= rightX; i++) {
                System.out.print(arr[i][leftY] + " ");
            }
            //其他情况，顺时针转圈打印，注意边界的处理
        } else {

            for (int i = leftY; i < rightY; i++) {
                System.out.print(arr[leftX][i] + " ");
            }
            for (int i = leftX; i <= rightX; i++) {
                System.out.print(arr[i][rightY] + " ");

            }
            for (int i = rightY - 1; i >= leftY; i--) {
                System.out.print(arr[rightX][i] + " ");
            }

            for (int i = rightX - 1; i > leftX; i--) {
                System.out.print(arr[i][leftY] + " ");

            }

            print(arr, ++leftX, ++leftY, --rightX, --rightY);
        }

    }

    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}

        };
        int[][] arr1 = {
                {1, 2, 3, 4},
        };

        int[][] arr2 = {
                {1}, {2},{3}, {4},
        };
        clockwisePrintMatrix(arr);
        System.out.println();
        clockwisePrintMatrix(arr1);
        System.out.println();
        clockwisePrintMatrix(arr2);

        /* result：
        * 1 2 3 4 8 12 16 15 14 13 9 5 6 7 11 10
        * 1 2 3 4
        * 1 2 3 4
        * */
    }

}
