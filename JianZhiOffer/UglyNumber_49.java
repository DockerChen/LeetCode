public class UglyNumber_49 {
    public int getUglyNumber(int index) {
        if(index==0){
            return 0;
        }
        int res = 0;
        int count = 0;
        int i = 1;
        while (true) {
            if (judge(i)) {
                count++;
                if (count == index) {
                    res = i;
                    break;
                }
            }
            i++;
        }
        return res;
    }


    private Boolean judge(int num) {
        while (num % 2 == 0) {
            num = num / 2;
        }
        while (num % 3 == 0) {
            num = num / 3;
        }
        while (num % 5 == 0) {
            num = num / 5;
        }
        if (num == 1) {
            return true;
        }
        return false;
    }

    public int getUglyNumberByDP(int index) {
        if(index==0){
            return 0;
        }
        int i2 = 0, i3 = 0, i5 = 0;
        int[] dp = new int[index];
        dp[0] = 1;
        for (int i = 1; i < index; i++) {
            int next2 = dp[i2] * 2, next3 = dp[i3] * 3, next5 = dp[i5] * 5;
            dp[i] = Math.min(next2, Math.min(next3, next5));
            if (dp[i] == next2) {
                i2++;
            }
            if (dp[i] == next3)
                i3++;
            if (dp[i] == next5)
                i5++;
        }
        return dp[index - 1];

    }



    public static void main(String[] args) {
        int num = new UglyNumber_49().getUglyNumber(1500);
        System.out.println(num);
        int num_1 = new UglyNumber_49().getUglyNumberByDP(1500);
        System.out.println(num_1);

    }
}
