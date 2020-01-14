import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

public class TwoNumber_1 {
    //暴力,时间复杂度O(n^2)
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }

        throw new IllegalArgumentException("no two sum solution");

    }

    //hashmap,额外空间O(n)，时间复杂度O(n)
    public int[] twoSum_1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        int res = 0;

        for (int i = 0; i < nums.length; i++) {
            res = target - nums[i];
            if (map.containsKey(res) && map.get(res) != i) {
                return new int[]{i, map.get(res)};
            }
        }

        throw new IllegalArgumentException("no two sum solution");

    }


}
