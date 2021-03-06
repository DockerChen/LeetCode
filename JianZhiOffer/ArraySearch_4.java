public class ArraySearch_4 {
    public static void main(String[] args) {
        int[][] array = {{1, 2, 3}, {4, 5, 6}};
        int target = 9;
        boolean result = Find(target, array);
        System.out.println(result);


    }

    public static boolean Find(int target, int[][] array) {

        /*左下查找*/
        int rows = array.length;
        if (rows == 0) {
            return false;
        }
        int columns = array[0].length;
        if (columns == 0) {
            return false;
        }
        int column = 0;
        int row = rows - 1;
        //注意数组边界
        while (row >= 0 && column < columns) {
            if (target == array[row][column]) {
                return true;
            } else if (target < array[row][column]) {
                row--;
            } else {
                column++;
            }

        }
        return false;
    }
}
