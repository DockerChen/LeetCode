//数组拷贝
//clone
//System.arraycopy
//Arrays.copyOf
//都是浅拷贝

import java.util.Arrays;

public class TestArray {
    public static void main(String[] args) {
        int[][] a = {{1, 2, 3}, {4, 5, 6}};
        int[][] b = a.clone();
        int[][] c = new int[a.length][a[0].length];
        int[][] d = Arrays.copyOf(a, a.length);
        System.arraycopy(a, 0, c, 0, a.length);
        printArray(a);
        System.out.println("-----");
        printArray(b);
        System.out.println("-----");
        printArray(c);
        System.out.println("-----");
        printArray(d);
    }

    public static void printArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");

            }
            System.out.println();

        }
    }
}
