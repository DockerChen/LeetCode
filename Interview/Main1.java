
import java.util.Scanner;

public class Main1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int d = scanner.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = scanner.nextInt();
        for (int i = 0; i < n; i++)
            b[i] = scanner.nextInt();
        scanner.close();

        int hurt = 0;
        int low = 0, high = n - 1;
        quickSort(a, b, low, high);
        while (low <= high) {
            while (low <= high && d > a[low]) {
                low++;
                d++;
            }
            if (low <= high && d <= a[high]) {
                hurt += b[high--];
                d++;
            }
        }
        System.out.println(hurt);
    }

    public static void quickSort(int[] a, int[] b, int low, int high) {
        if (low < high) {
            int pivot = getPivot(a, b, low, high);
            quickSort(a, b, low, pivot - 1);
            quickSort(a, b, pivot + 1, high);
        }
    }

    public static int getPivot(int[] a, int[] b, int low, int high) {
        int piovt_a = a[low];
        int piovt_b = b[low];

        while (low < high) {
            while (low < high && a[high] >= piovt_a)
                high--;
            a[low] = a[high];
            b[low] = b[high];
            while (low < high && a[low] <= piovt_a)
                low++;
            a[high] = a[low];
            b[high] = b[low];
        }
        a[low] = piovt_a;
        b[low] = piovt_b;

        return low;
    }

}