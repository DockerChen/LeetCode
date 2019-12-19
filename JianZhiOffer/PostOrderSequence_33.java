import class_06.Edge;

public class PostOrderSequence_33 {
    public static boolean judge(int[] arr) {
        if (arr.length == 0) {
            return false;
        }

        return process(arr, 0, arr.length - 1);

    }

    public static boolean process(int[] arr, int start, int end) {
        if (end - start <= 0) {
            return true;
        }
//        System.out.println(start);
//        System.out.println(end);
        int root = arr[end];
        int cur = start;
        while (cur < end && arr[cur] < root) {
            cur++;
        }

        for (int i = cur; i < end; i++) {
            if (arr[i] < root) {
                return false;
            }
        }
        return process(arr, start, cur - 1) && process(arr, cur, end - 1);

    }

    public static void main(String[] args) {
        int[] arr1 = {5, 7, 6, 9, 11, 10, 8};
        int[] arr2 = {7, 4, 6, 5};
        int[] arr3 = {9, 11, 10, 8};

        System.out.println(judge(arr1));
        System.out.println(judge(arr2));
        System.out.println(judge(arr3));
    }

}
