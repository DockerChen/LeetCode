import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MoreThanHalfNumber_39 {
    //    摩尔投票算法 , 能够在 O (n) 的时间和 O (1) 的空间解决问题
    public static int getMoreThanHalfNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int majority = nums[0];
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = nums[i] == majority ? count + 1 : count - 1;

            if (count == 0) {
                majority = nums[i];
                count = 1;
            }

        }

        count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = majority == nums[i] ? count + 1 : count;
        }

        return count > nums.length / 2 ? majority : -1;

    }

    public static List<Integer> getMoreThan3_Number(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        int[] majority = new int[2];
        int count1 = 0, count2 = 0;
        //初始化要查找的两个不同的元素
        majority[0] = nums[0];
        majority[1] = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != majority[0]) {
                majority[1] = nums[i];
                break;
            }

        }

        //摩尔投票算法
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == majority[0]) {
                count1++;
            } else if (nums[i] == majority[1]) {
                count2++;
            } else if (count1 == 0) {
                majority[0] = nums[i];
                count1 = 1;
            } else if (count2 == 0) {
                majority[1] = nums[i];
                count2 = 1;
            } else {
                count1--;
                count2--;

            }

        }

        count1 = 0;
        count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            count1 = majority[0] == nums[i] ? count1 + 1 : count1;
            count2 = majority[1] == nums[i] ? count2 + 1 : count2;
        }

        List<Integer> list = new ArrayList<>();
        //最后再遍历一次数组，确认元素是否超过n/3次
        if (count1 > nums.length / 3) {
            list.add(majority[0]);
        }
        if (count2 > nums.length / 3) {
            list.add(majority[1]);
        }

        return list;

    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        System.out.println(getMoreThanHalfNumber(arr));
    }

}
