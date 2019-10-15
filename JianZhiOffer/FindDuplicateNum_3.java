public class FindDuplicateNum_3 {
    public static boolean findDuplicateNum(int[] arr, int length, int[] dup) {
        if (arr == null || length <= 0) {
            return false;
        }
        //时间复杂度O(n)
        for (int i = 0; i < length; i++) {
            //每个数字最多交换2次
            while (arr[i] != i) {
                if (arr[i] == arr[arr[i]]) {
                    dup[0] = arr[i];
                    return true;
                }
                swap(arr, i, arr[i]);
            }
        }
        return false;

    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    //不修改数组找出重复数字
    public static int findDuplicateNumNoEdit(int[] arr, int length) {
        if (arr == null || length <= 0) {
            return -1;
        }
        int start = 1;
        int end = length - 1;

        while (start <= end) {

            int mid = start + ((end - start) >> 1);
            int count = getCount(arr, length, start, mid);
//            System.out.println(mid+" "+count);
            if (start == end) {
                if (count > 1) {
                    return start;
                } else {
                    return -1;
                }
            }
            if (count > (mid - start + 1)) {
                end = mid;
            } else {
                start = mid + 1;
            }

        }
        return -1;
    }

    private static int getCount(int[] arr, int length, int start, int end) {
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (arr[i] >= start && arr[i] <= end) {
                count++;
            }
        }
        return count;
    }

    private static void test(int[] arr, int length, int[] dup) {
        int result = findDuplicateNumNoEdit(arr, length);
        for (int i = 0; i < dup.length; i++) {
            if (result == dup[i]) {
                System.out.println("good");
                return;
            }

        }
        System.out.println("fuck!");
    }

    public static void main(String[] args) {
        int numbers[] = {2, 3, 5, 4, 3, 2, 6, 7};
        int duplications[] = {2, 3};
        System.out.println("test");
        test(numbers, numbers.length, duplications);

    }

}
