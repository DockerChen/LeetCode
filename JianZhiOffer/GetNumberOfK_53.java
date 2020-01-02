public class GetNumberOfK_53 {
    public int getNumberOfK(int[] array, int k) {
        // 二分查找，时间复杂度O(logn),logn是while循环的次数。
        int first = getFirst(array, k);
        int last = getLast(array, k);
//        System.out.println(first + " " + last);
        return last - first + 1;
    }


    //获取k第一次出现的下标
    int getFirst(int[] data, int k) {
        int start = 0, end = data.length - 1;
        int mid = (start + end) / 2;
        while (start <= end) {
            if (data[mid] < k) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
            mid = (start + end) / 2;
        }
        // start指向第一个小于k的数的下标，while循环结束，返回的start表示k第一次出现的下标。
        // 如果k不存在，则返回第一个大于k的数的下标。
        return start;
    }

    //获取k最后一次出现的下标
    int getLast(int[] data, int k) {
        int start = 0, end = data.length - 1;
        int mid = (start + end) / 2;

        while (start <= end) {
            if (data[mid] <= k) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
            mid = (start + end) / 2;
        }
        // end指向第一个大于k的数的下标，while循环结束，返回的end表示k最后一次出现的下标。
        // 如果k不存在，则返回第一个小于k的数的下标。
        return end;
    }


    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 3, 3, 3, 4, 6};
        int[] arr2 = {3, 3, 3, 3, 4, 5};
        int[] arr3 = {1, 2, 3, 4, 6};
        System.out.println(new GetNumberOfK_53().getNumberOfK(arr1, 3));
        System.out.println(new GetNumberOfK_53().getNumberOfK(arr2, 3));
        System.out.println(new GetNumberOfK_53().getNumberOfK(arr3, 5));
    }
}
