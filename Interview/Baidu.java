import java.util.*;

public class Baidu {
    int res = 0;

    public static void main(String[] args) {
        Baidu main = new Baidu();
//        main.s1();
        main.s2();
//        main.s3();
//        System.out.println(baidu.gcd_1(6, 4));

    }

    public void s1() {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextInt();
        long res = 0;
//        System.out.println(n * (n - 1) - 1);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    long tmp = lcm(i, j) - gcd(i, j);
                    res = Math.max(res, tmp);
                }

            }

        }
        System.out.println(res);

    }

    public long lcm(long a, long b) {
        long tmp = gcd(a, b);
        long res = a * b / tmp;
        return res;

    }

    public long gcd(long a, long b) {
        long n = 0;
        while (b != 0) {
            n = a % b;
            a = b;
            b = n;
        }
        return a;

    }

    public void s2() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextLong();
        }
        long res = 0;
        //记录数组当前的最大值
        long max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        while (max >= n) {
            res += getRes(arr);
            max = 0;
            for (int i = 0; i < arr.length; i++) {
                max = Math.max(max, arr[i]);
//                System.out.println(max);
            }

        }

        System.out.println(res);

    }

    public int getRes(long[] arr) {
        int n = arr.length;
        long[] values = new long[n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            values[i] = arr[i] / n;
            res += values[i];
            System.out.print(values[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            if (arr[i] >= n) {
                arr[i] -= n * values[i];
                arr[i] += res - values[i];
            } else {
                arr[i] += res - values[i];
            }
        }
        return res;

    }

    public void reverse(long[] arr) {
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            long tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i++;
            j--;
        }
    }

    public void s3() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 1; i <= n - 1; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            List<Integer> list1 = map.get(a) == null ? new ArrayList<>() : map.get(a);
            list1.add(b);
            map.put(a, list1);
            List<Integer> list2 = map.get(b) == null ? new ArrayList<>() : map.get(b);
            list2.add(a);
            map.put(b, list2);

        }

        for (int i = 1; i <= n; i++) {
            helper(values, map, 1, i);
        }
        System.out.println(res);

    }

    public void helper(int[] nums, Map<Integer, List<Integer>> map, int step, int nowPoint) {
        //邻接点
        List<Integer> neighbor = map.get(nowPoint);
        //可以走的点
        List<Integer> canDo = new ArrayList<>();
        //判断该点的邻接点是否满足严格递增的条件
        int flag = 0;
        for (Integer dst : neighbor) {
            if (nums[dst - 1] > nums[nowPoint - 1]) {
                flag = 1;
                canDo.add(dst);
            }
        }

        if (flag == 0) {
            if (step > res) {
                res = step;
            }
        } else {
            for (Integer dst : canDo) {
                helper(nums, map, step + 1, dst);
            }

        }

    }

    public int gcd_1(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd_1(b, a % b);
        }
    }
}
