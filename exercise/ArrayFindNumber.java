public class ArrayFindNumber {

    public static boolean arrayFindNumber(int[][] arr, int num) {
        int arrRow = arr.length - 1;
        int arrColumn = arr[0].length - 1;
        //从右上方开始查找
        int sRow = 0;
        int sColumn = arrColumn;
        while (sRow <= arrRow && sColumn >= 0) {
            if (arr[sRow][sColumn] < num) {
                sRow++;
            } else if (arr[sRow][sColumn] > num) {
                sColumn--;
            } else {
                return true;
            }
        }
        return false;


    }

    public static void main(String[] args) {
        int[][] arr = {{0, 1, 2, 5}, {2, 3, 4, 7}, {4, 4, 4, 8}, {5, 7, 7, 9}};
        int num = 6;
        if (arrayFindNumber(arr, num)) {
            System.out.println("true");

        } else {
            System.out.println("false");
        }

    }
}
