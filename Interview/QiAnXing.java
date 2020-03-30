import java.util.Scanner;

public class QiAnXing {
    static int ans = 0;

    public static void s1() {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int res = 0;
        int[] numbers = {2, 3, 1, 5, 4, 3};
        int[] values = {2, 2, 3, 1, 5, 2};
        int[] counts = {1, 1, 1, 1, 1, 1};
        helper(numbers, values, counts, N, 0);
        System.out.println(ans);

    }

    public static void helper(int[] number, int[] values, int[] counts, int money, int cnt) {
        if (money == 0) {
            if (cnt > ans) {
                ans = cnt;
            }
        }
        //标志位，判断所有的口罩品牌是否买完，以及能否购买得起口罩
        int flag = 0;
        for (int i = 0; i < number.length; i++) {
            if (money >= values[i] && counts[i] == 1) {
                flag = 1;
                counts[i] = 0;
                helper(number, values, counts, money - values[i], cnt + number[i]);
                counts[i] = 1;

            }
        }

        if (flag == 0) {
            if (cnt > ans) {
                ans = cnt;
            }
        }

    }

    public static void s2() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] strs = str.split(",");
        int n = strs.length;
        int[] nums = new int[n];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(strs[i]);
        }

        int sum1 = 0;
        int sum2 = 0;
        int sum3 = 0;

        for (int i = 0; i <= 3; i++) {
            sum1 += nums[i];
        }
        for (int i = 3; i <= 6; i++) {
            sum2 += nums[i];
        }
        sum3 = nums[6] + nums[7] + nums[8] + nums[0];

        if (sum1 == sum2 && sum2 == sum3) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }

    }

    public static void main(String[] args) {
        s1();

    }
}
