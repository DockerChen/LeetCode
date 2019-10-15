import org.jetbrains.annotations.NotNull;

public class FindMinNumberInRotateArray_11 {
    public static int findByDichotomy(int[] arr) {
        if (arr[0] < arr[arr.length - 1]) {
            return arr[0];
        }
        int p1 = 0;
        int p2 = arr.length - 1;
        int mid = 0;
        int min = arr[0];
        while (arr[p1] >= arr[p2]) {
            if (p1 + 1 == p2) {
                min = arr[p2];
                break;
            }

            mid = p1 + ((p2 - p1) >> 1);

            if (arr[mid] == arr[p1] && arr[mid] == arr[p2]) {
                return getMin(arr, p1, p2);

            }
            if (arr[mid] >= arr[p1]) {
                p1 = mid;

            } else if (arr[mid] <= arr[p2]) {
                p2 = mid;
            }
        }
        return min;

    }

    private static int getMin(int[] arr, int p1, int p2) {
        int min = arr[p1];
        for (int i = p1 + 1; i < p2; i++) {
            if (min > arr[i]) {
                min = arr[i];
            }
        }
        return min;
    }

    public static int findByForce(int[] arr) {
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];
            }
        }
        return min;
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, 5, 1, 2};
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {1, 0, 1, 1, 1};
        int[] arr3 = {1};
        System.out.println(comparator(arr));
        System.out.println(comparator(arr1));
        System.out.println(comparator(arr2));
        System.out.println(comparator(arr3));

    }

    @NotNull
    public static String comparator(int[] arr) {
        return findByDichotomy(arr) == findByForce(arr) ? "true" : "false";
    }

}
