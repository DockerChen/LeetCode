import java.util.ArrayDeque;
import java.util.ArrayList;

public class MaxInWindows_59_1 {
    //常规遍历，时间复杂度O(size*n)
    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> res = new ArrayList<>();
        if (num != null && num.length != 0) {
            if (size > 0 && size <= num.length) {
                int index = 0;
                while (index + size <= num.length) {
                    int max = Integer.MIN_VALUE;
                    for (int i = index; i < size + index; i++) {
                        max = Math.max(max, num[i]);
                    }
                    res.add(max);
                    index++;
                }
            }
        }
        return res;

    }

    //时间复杂度O(n)
    public ArrayList<Integer> maxInWindows_2(int[] num, int size) {
        // 用一个双端队列，队列第一个位置保存当前窗口的最大值，每当窗口滑动一次，进行下面的判断：
        // 1.判断当前最大值是否过期
        // 2.新增加的值从队尾开始比较，把所有小于等于它的值从队列中移除，然后再添加新增的值
        ArrayList<Integer> res = new ArrayList<>();
        if (num != null && num.length != 0) {
            if (size > 0 && size <= num.length) {
                ArrayDeque<Integer> deque = new ArrayDeque<>();
                int begin = 0;
                for (int i = 0; i < num.length; i++) {
                    //begin记录滑动窗口的起始位置
                    begin = i - size + 1;
                    if (deque.isEmpty()) {
                        deque.addLast(i);
                    }
                    // 判断当前最大值是否过期
                    else if (begin > deque.peekFirst()) {
                        deque.pollFirst();
                    }
                    // 新增加的值从队尾开始比较，把所有小于等于它的值从队列中移除，然后再添加新增的值
                    while (!deque.isEmpty() && num[deque.peekLast()] <= num[i]) {
                        deque.pollLast();
                    }
                    deque.addLast(i);
                    // 添加当前滑动窗口的最大值
                    if (begin >= 0) {
                        res.add(num[deque.peekFirst()]);
                    }
                }

            }
        }


        return res;
    }

}
