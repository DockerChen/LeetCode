public class InversePairs_51 {
    // O(n^2)

    public int InversePairs(int[] array) {
        if (array == null || array.length < 2) {
            return 0;
        }
        long res = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    res++;
                }
            }
        }
        return (int) (res % 1000000007);
    }

    //辅助数组
    private int[] help;

    // O(nlogn)
    public int InversePairsByMergeSort(int[] array) {
        if (array == null || array.length < 2) {
            return 0;
        }

        return (int) (mergeSort(array, 0, array.length - 1) % 1000000007);

    }
    // 归并排序
    private long mergeSort(int[] array, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        return mergeSort(array, left, mid) + mergeSort(array, mid + 1, right) + merge(array, left, mid, right);

    }

    private long merge(int[] array, int left, int mid, int right) {
        help = new int[right - left + 1];
        int i = 0;
        int p1 = left;
        int p2 = mid + 1;
        long res = 0;
        // 关键代码
        // 每次合并的时候，统计逆序对的个数，合并完成之后，局部有序。使用归并排序，可以减少重复的比较次数，从而缩短时间复杂度。
        while (p1 <= mid && p2 <= right) {
            if (array[p1] > array[p2]) {
                for (int j = p2; j <= right; j++) {
                    res++;
                }
            }
            help[i++] = array[p1] > array[p2] ? array[p1++] : array[p2++];
        }

        while (p1 <= mid) {
            help[i++] = array[p1++];
        }
        while (p2 <= right) {
            help[i++] = array[p2++];
        }

        for (int j = 0; j < help.length; j++) {
            array[left + j] = help[j];
        }

        return res;


    }

    public static void main(String[] args) {
        int[] arr = {7, 5, 6, 4};
        //        System.out.println(new InversePairs_51().InversePairs(arr));
        System.out.println(new InversePairs_51().InversePairsByMergeSort(arr));


    }
}
