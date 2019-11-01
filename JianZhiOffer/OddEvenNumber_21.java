public class OddEvenNumber_21 {
    public void adjust(int[] arr) {
        int begin = 0;
        int end = arr.length - 1;
        while (begin < end) {
            while (judgeOddEven(arr[begin]) && begin < end) {
                begin++;
            }
            while (!judgeOddEven(arr[begin]) && begin < end) {
                end--;
            }
            swap(arr, begin, end);

        }
    }

    public boolean judgeOddEven(int i) {
        return i % 2 == 0;
    }

    public void swap(int[] arr, int begin, int end) {
        int tmp = arr[begin];
        arr[begin] = arr[end];
        arr[end] = tmp;
    }

    public void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        OddEvenNumber_21 oddEvenNumber_21 = new OddEvenNumber_21();
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 20);
        }
        oddEvenNumber_21.printArray(arr);
        oddEvenNumber_21.adjust(arr);
        oddEvenNumber_21.printArray(arr);

    }
}
