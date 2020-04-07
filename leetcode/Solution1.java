import java.util.Arrays;

public class Solution1 {
    public int[] getLeastNumbers(int[] arr, int k) {
        int[] res = new int[k];
        Arrays.sort(arr);
        for (int i = 0; i < k; i++) {
            res[i] = arr[i];
        }
        return res;

    }

    public int lastRemaining(int n, int m) {
        int pos = 0;
        for (int i = 2; i <= n; i++) {
            pos = (pos + m) % i;
        }
        return pos;

    }

    public int massage(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int n = nums.length;
        //dp[i][0]表示第i-1天没有预约情况下的最大值
        //dp[i][1]表示第i-1天预约情况下的最大值
        int[][] dp = new int[n][n];
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = Math.max(dp[i][1], dp[i - 1][0] + nums[i]);
        }
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }

    //O(nlogn),排序
    public int minIncrementForUnique(int[] A) {
        Arrays.sort(A);
        int res = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i] <= A[i - 1]) {
                int pre = A[i];
                A[i] = A[i - 1] + 1;
                res += A[i] - pre;

            }
        }
        return res;

    }

    //O(n)，计数法
    public int minIncrementForUnique_1(int[] A) {
        int[] count = new int[40000];
        int max = 0;
        for (int num : A) {
            count[num]++;
            max = Math.max(max, num);
        }
        int res = 0;
        for (int i = 0; i < max; i++) {
            if (count[i] > 1) {
                res += count[i] - 1;
                count[i + 1] += count[i] - 1;
            }
        }
        if (count[max] > 1) {
            int d = count[max] - 1;
            res += (1 + d) * d / 2;
        }
        return res;

    }

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        System.out.println(solution1.lastRemaining(5, 3));

    }
}
