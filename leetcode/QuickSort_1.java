import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QuickSort_1 {
    public static void quicksort_1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quicksort_1(arr, 0, arr.length - 1);
    }

    public static void quicksort_1(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int tmp = arr[left];
        int i = left;
        int j = right;
        while (i < j) {
            while (arr[j] >= tmp && i < j) {
                j--;
            }
            while (arr[i] <= tmp && i < j) {
                i++;
            }
            if (i < j) {
                swap(arr, i, j);
            }
        }
        swap(arr, left, i);
        quicksort_1(arr, left, i - 1);
        quicksort_1(arr, i + 1, right);
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length - 1; i++) {
            arr[i] = (int) (Math.random() * 20 + 1);
        }
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        quicksort_1(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + " ");
        }


    }
}


