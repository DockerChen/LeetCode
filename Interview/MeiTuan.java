import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class MeiTuan {
    public static void main(String[] args) {
        MeiTuan test = new MeiTuan();
        System.out.println(test.getMaxStrLen());

//        System.out.println(test.s1());
//        System.out.println(test.s2());
//        System.out.println(test.s3());

    }

    public long s1() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr1 = new int[n];
        int[] arr2 = new int[n];
        for (int i = 0; i < n; i++) {
            arr1[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            arr2[i] = scanner.nextInt();
        }

        long res1 = getMax(arr1);
        long res2 = getMax(arr2);
        return Math.max(res1, res2);

    }

    public long getMax(int[] arr) {
        if (arr.length < 3) {
            return 0;
        }
        long max = 0, max1, max2, max3;
        int[] tmp = new int[3];
        System.arraycopy(arr, 0, tmp, 0, 3);
        for (int i = 3; i < arr.length; i++) {
            max = tmp[0] + tmp[1] + tmp[2];
            max1 = tmp[0] + tmp[1] + arr[i];
            max2 = tmp[0] + tmp[2] + arr[i];
            max3 = tmp[1] + tmp[2] + arr[i];
            max = Math.max(max, max1);
            max = Math.max(max, max2);
            max = Math.max(max, max3);
            tmp[2] = max == max1 ? arr[i] : tmp[2];
            tmp[1] = max == max2 ? arr[i] : tmp[1];
            tmp[0] = max == max3 ? arr[i] : tmp[0];

        }
        return max;

    }

    public int s2() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] num = new int[n];
        for (int i = 0; i < n; i++) {
            num[i] = scanner.nextInt();
        }
        int[] dp = new int[n];
        dp[0] = 1;
        int ans = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = num[i] > num[i - 1] ? dp[i - 1] + 1 : 1;
            if (ans < dp[i]) {
                ans = dp[i];
            }
        }
        for (int i = 1; i < n - 1; i++) {
            //删掉一个数字，会导致ans的值增大，不变或者减少（减少需要另外考虑）
            //只有高低高和低高低的子串，才有可能增大ans
            if (num[i - 1] < num[i + 1]) {
                if ((dp[i] == 1 && dp[i + 1] == dp[i] + 1)
                        || (dp[i] == dp[i - 1] + 1 && dp[i + 1] == 1)) {
                    int k = i + 1;
                    while (k < n && dp[k] != 1) {
                        k++;
                    }
                    int ans2 = dp[i - 1] + k - i - 1;
                    if (ans < ans2) {
                        ans = ans2;
                    }

                }
            }
        }
        //ans==n，说明数组num是单调递增的，最大递增子串长度为ans-1
        return ans == n ? ans - 1 : ans;
    }

    public int s3() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int m = scanner.nextInt();
        int p = scanner.nextInt();
        int q = scanner.nextInt();
        int[] arr = new int[k];
        Arrays.sort(arr);
        int sum = 0;
        for (int i = 0; i < k; i++) {
            arr[i] = scanner.nextInt();
            sum += arr[i];
        }
        Arrays.sort(arr);
        int res = 0;
        if (m >= sum) {
            if ((m / sum) >= n) {
                res = (p * k + q) * n;
                return res;
            } else {
                res = (p * k + q) * (m / sum);
                n = n - (m / sum);
                m = m - (m / sum) * sum;
            }
        }

        if (m > 0 && n > 0) {
            for (int i = 0; i < k; i++) {
                if (m < arr[i]) {
                    break;
                } else {
                    int tmp = m / arr[i];
                    if (tmp >= n) {
                        res += p * n;
                        break;
                    } else {
                        res += p * tmp;
                        m = m - tmp * arr[i];
                    }

                }
            }
        }
        return res;

    }

    public long getMaxStrLen() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] strs = new String[n];
        for (int i = 0; i < n; i++) {
            strs[i] = scanner.next();
        }
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        long maxLen = strs[0].length();
        long tmp = maxLen;
        for (int i = 1; i < strs.length; i++) {
            char pre = strs[i - 1].charAt(strs[i - 1].length() - 1);
            char cur = strs[i].charAt(0);
            if (pre <= cur) {
                tmp += strs[i].length();
            } else {
                tmp = strs[i].length();
            }
            maxLen = Math.max(maxLen, tmp);
        }
        return maxLen;
    }
}
