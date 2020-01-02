import java.util.Arrays;

public class Reverse_order {


    public static void reverse_order(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        System.out.println("reverse order:" + mergeSort(arr, 0, arr.length - 1));

    }

    /*归并排序*/
    public static int mergeSort(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        return mergeSort(arr, left, mid) + mergeSort(arr, mid + 1, right) + merge(arr, left, mid, right);

    }


    public static int merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int i = 0;
        int p1 = left;
        int p2 = mid + 1;
        int res = 0;
        while (p1 <= mid && p2 <= right) {
            if (arr[p1] > arr[p2]) {
                for (int j = p2; j <= right; j++) {
                    res++;
                    System.out.println(arr[p1] + "," + arr[j]);
                }
            }
            help[i++] = arr[p1] > arr[p2] ? arr[p1++] : arr[p2++];

        }

        /*下面两个循环只有一个会执行*/
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }

        while (p2 <= right) {
            help[i++] = arr[p2++];
        }

        for (int j = 0; j < help.length; j++) {
            arr[left + j] = help[j];

        }
        return res;


    }


    /*对数器*/
    /*绝对正确的方法*/

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random() - (maxValue + 1) * Math.random());
        }
        return arr;

    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;


    }


    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
//        int[] arr=generateRandomArray(maxSize,maxValue);
        int[] arr = {7, 5, 6, 4};
        printArray(arr);
        reverse_order(arr);
        printArray(arr);


    }

}
