public class cutRope_14 {
    //      思路：
    //      f(n)=max(f(i)*f(n-i)),0<i<n
    //      f(n)表示把绳子剪成若干段后各段乘积的最大值
    //1.递归
    public int cutRope(int target) {
        if (target < 2) {
            return 0;
        }
        if (target == 2) {
            return 1;
        }
        if (target == 3) {
            return 2;
        }
        int max = 0;

        for (int i = 1; i <= (target - 1) / 2; i++) {
            max = Math.max(max, process(i) * process(target - i));
        }

        return max;
    }

    public int process(int target) {
        //递归终止条件
        if (target < 4) {
            return target;
        }
        int max = 0;
        for (int i = 1; i <= (target - 1) / 2; i++) {

            max = Math.max(max, process(i) * process(target - i));
        }

        return max;

    }

    //2.动态规划,时间复杂度O(n^2),由递归转化而来
    public int cutRopeDP(int target) {
        if (target < 2) {
            return 0;
        }
        if (target == 2) {
            return 1;
        }
        if (target == 3) {
            return 2;
        }
        int[] dp = new int[target + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i <= target; i++) {
            for (int j = 1; j <= i / 2; j++) {

                dp[i] = Math.max(dp[i], dp[j] * dp[i - j]);
            }
        }
        return dp[target];

    }

    //    3.贪心，时间复杂度O(1)
    //    n>4时，划分出尽可能多的3，因为3(n-3)>=2(n-2)
    //    n=4时，2*2 > 3*1,所以当划分出1和3时，要转变成2和2
    //    n<4时，特殊情况，单独处理
    public int cutRopeGreedy(int target) {
        if (target < 2) {
            return 0;
        }
        if (target == 2) {
            return 1;
        }
        if (target == 3) {
            return 2;
        }
        int timeOf3 = target / 3;
        if (target - timeOf3 * 3 == 1) {
            timeOf3--;
        }

        int timeOf2 = (target - timeOf3 * 3) / 2;
        int res = (int) (Math.pow(3, timeOf3) * Math.pow(2, timeOf2));
        return res;

    }

    //测试
    public static void main(String[] args) {
        cutRope_14 cutRope_14 = new cutRope_14();
        System.out.println(cutRope_14.cutRope(14));
        System.out.println(cutRope_14.cutRopeDP(14));
        System.out.println(cutRope_14.cutRopeGreedy(14));
    }

}