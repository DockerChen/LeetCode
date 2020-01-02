public class MissingNumber_53_2 {
    //有序数组，使用二分查找
    public int getMissingNumber(int[] arr, int len) {
        //边界处理
        if (arr == null || len <= 0) {
            return -1;
        }

        int left = 0;
        int right = len - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] != mid) {
                if (mid == 0 || arr[mid - 1] == mid - 1) {
                    return mid;
                }
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        if (left == len) {
            return left;
        }
        return -1;


    }
}
