public class NetherlandsFlag {
    public static int[] netherlandsflag(int[] arr, int left, int right, int num) {
        int less = left - 1;
        int more = right + 1;
        int cur = left;
        while (cur < more) {
            if (arr[cur] < num) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > num) {
                swap(arr, cur, --more);
            } else {
                cur++;
            }
        }
        return new int[]{less , more };
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static int[] generateArray() {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10);
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");

        }
        System.out.println();

    }


    public static void main(String[] args) {
        while (true) {
            int[] arr = generateArray();
//            printArray(arr);
            int num = arr[5];

            int[] res = netherlandsflag(arr, 0, arr.length - 1, 5);
            if (res[0] > res[1]) {
                printArray(arr);
                System.out.println(5 + "--" + res[0] + "," + res[1]);
                break;
            }
        }

    }
}
