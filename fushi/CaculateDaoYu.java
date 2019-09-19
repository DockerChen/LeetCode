import java.util.Scanner;

public class CaculateDaoYu {
    //我们遍历矩阵的每一个点，对每个点都尝试进行一次深度优先搜索，如果搜索到 1，就继续向它的四周搜索。
    // 同时我们每找到一个 1，就将其标为 0，这样就能把整个岛屿变成 0。
    // 我们只要记录对矩阵遍历时能进入多少次搜索，就代表有多少个岛屿。
    public static int caculateDaoyu(int[][] arr) {
        int[][] tmp =new int[arr.length][arr[0].length];
        for (int i = 0; i < tmp.length; i++) {
            for (int j = 0; j < tmp[0].length; j++) {
                tmp[i][j]=arr[i][j];

            }

        }
        TestArray testArray=new TestArray();
        testArray.printArray(tmp);
        int num_lands = 0;
        for (int i = 0; i < tmp.length; i++) {
            for (int j = 0; j < tmp[0].length; j++) {
                if (tmp[i][j] == 1) {
                    searchIsLand(tmp, i, j);
                    num_lands++;
                }
            }
        }
        return num_lands;
    }
    //深度优先搜索
    public static void searchIsLand(int[][] arr, int i, int j) {
        arr[i][j] = 0;
        if (i > 0 && arr[i - 1][j] == 1)
            searchIsLand(arr, i - 1, j);
        if (i < arr.length - 1 && arr[i + 1][j] == 1)
            searchIsLand(arr, i + 1, j);
        if (j > 0 && arr[i][j - 1] == 1) {
            searchIsLand(arr, i, j - 1);
        }
        if (j < arr.length - 1 && arr[i][j + 1] == 1) {
            searchIsLand(arr, i, j + 1);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int row, column, k;
        row = scanner.nextInt();
        column = scanner.nextInt();
        k = scanner.nextInt();
        int[][] arr = new int[row][column];
        int arr_row, arr_cloumn;
        while (k-- >0) {
            arr_row = scanner.nextInt();
            arr_cloumn = scanner.nextInt();
            arr[arr_row][arr_cloumn] = 1;
//            TestArray testArray=new TestArray();
//            testArray.printArray(arr);
            System.out.println(caculateDaoyu(arr));

        }

    }
}
