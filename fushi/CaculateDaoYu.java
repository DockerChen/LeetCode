import java.util.Scanner;

public class CaculateDaoYu {
    public static int caculateDaoyu(int[][] arr, int arr_row, int arr_column) {
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int row, column, k;

        row = scanner.nextInt();
        column = scanner.nextInt();
        k = scanner.nextInt();
        int[][] arr = new int[row][column];
        int arr_row, arr_cloumn;
        int res = 0;
        while (k-- >= 0) {
            arr_row = scanner.nextInt();
            arr_cloumn = scanner.nextInt();
            arr[arr_row][arr_cloumn] = 1;
            res += caculateDaoyu(arr, arr_row, arr_cloumn);
            System.out.println(res);


        }


    }
}
