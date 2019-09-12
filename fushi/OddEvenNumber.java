/*给定一个整形数组，奇数放左边，偶数放右边*/
public class OddEvenNumber {
    public static int[] generateArray() {
        int[] array = new int[1000];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * Integer.MAX_VALUE);
        }
        return array;
    }

    public static int oddevenNumber(int[] arr) {
        int begin = 0;
        int end = arr.length - 1;
        while (begin < end) {
            while (arr[begin] % 2 == 1 && begin < end) {
                begin++;
            }
            while (arr[end] % 2 == 0 && begin < end) {
                end--;
            }
            swap(arr, begin, end);


        }
        return begin;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;

    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        for (int i = 1; i <= 10; i++) {
//            int[] arr=generateArray();
//            System.out.println("---"+i+"---");
//            printArray(arr);
//            System.out.println(oddevenNumber(arr));
//            printArray(arr);
//            System.out.println((Integer.MAX_VALUE));
//
//        }
        int[] arr = generateArray();
        System.out.println(oddevenNumber(arr));
        printArray(arr);
        System.out.println((Integer.MAX_VALUE));

    }
}
