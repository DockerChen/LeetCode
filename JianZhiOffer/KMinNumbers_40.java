import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class KMinNumbers_40 {
    public static ArrayList<Integer> getKMinNumbers(int[] nums, int k) {

        if (k > nums.length || k <= 0) {
            return new ArrayList<>();
        }

        //用最大堆来存储
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        //最大堆中的结点个数超过k个，就移除堆顶结点
        for (int i = 0; i < nums.length; i++) {
            maxHeap.add(nums[i]);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        return new ArrayList<>(maxHeap);

    }

}
