import java.util.Comparator;
import java.util.PriorityQueue;

public class TheKMaxNumber_215 {
    public int findKthLargest(int[] nums, int k) {

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int i = 0; i < nums.length; i++) {
            maxHeap.add(nums[i]);
        }
        int count = 0;
        int num = 0;

        while (count != k) {
            num = maxHeap.poll();
            count++;
        }

        return num;

    }

    public int findKthLargestByQuickSort(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        int k_index = nums.length - k;
        while (left < right) {
            int index = partition(nums, left, right);
            if (index == k_index) {
                break;
            } else if (index > k_index) {
                right = index - 1;
            } else {
                left = index + 1;
            }

        }
        return nums[k_index];

    }

    //快排
    private int partition(int[] nums, int left, int right) {
        int p = nums[left];
        int i = left, j = right + 1;
        while (true) {
            while (i != right && nums[++i] < p) ;
            while (j != left && nums[--j] > p) ;
            if (i >= j) {
                break;
            }
            swap(nums, i, j);
        }
        swap(nums,left,j);
        return j;


    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
