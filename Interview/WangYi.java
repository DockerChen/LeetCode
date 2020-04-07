import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class WangYi {
    public void s1() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextLong();
        }

        if (n <= 1) {
            System.out.println(-1);
            return;
        }
        long[] gap = new long[n - 1];
        for (int i = 0; i < n - 1; i++) {
            gap[i] = arr[i + 1] - arr[i];
        }

        Arrays.sort(gap);
        long d = gap[0];
        if (d <= 0) {
            System.out.println(-1);
        } else {
            for (int i = 0; i < n - 1; i++) {
                if (gap[i] % d != 0) {
                    System.out.println(-1);
                    return;
                }
            }
            System.out.println(d);

        }

    }

    public int s2() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int D = scanner.nextInt();
        int[] breakGuard = new int[n];
        int[] hurt = new int[n];
        for (int i = 0; i < n; i++) {
            breakGuard[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            hurt[i] = scanner.nextInt();
        }

        helper(breakGuard, hurt, n, D, 0);
        return res;

    }

    int res = Integer.MAX_VALUE;

    public void helper(int[] breakGuard, int[] hurt, int n, int D, int index) {
        if (index == n - 1) {
            int sum = 0;
            int cur = D;
            for (int i = 0; i < n; i++) {
                sum += breakGuard[i] >= cur ? hurt[i] : 0;
                cur++;
            }
            res = Math.min(res, sum);
            return;

        } else {
            for (int i = 0; i < n; i++) {
                swap(breakGuard, index, i);
                swap(hurt, index, i);
                helper(breakGuard, hurt, n, D, index + 1);
                swap(breakGuard, index, i);
                swap(hurt, index, i);
            }
        }

    }

    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public int s3() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int f = scanner.nextInt();
        int q;
        int ans = 0;
        boolean[] mark = new boolean[n];
        Arrays.fill(mark, false);
        mark[f] = true;
        for (int i = 0; i < m; i++) {
            q = scanner.nextInt();
            int[] arr = new int[q];
            boolean flag = false;
            for (int j = 0; j < q; j++) {
                arr[j] = scanner.nextInt();
                if (mark[arr[j]]) {
                    flag = true;
                }
            }

            if (flag) {
                for (int j = 0; j < q; j++) {
                    mark[arr[j]] = true;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            ans += mark[i] ? 1 : 0;
        }

        return ans;

    }

    public int[][] s4() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            String str = scanner.next();
            for (int j = 0; j < str.length(); j++) {
                arr[i][j] = Integer.valueOf(str.charAt(j) + "");
            }
        }

//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 0; j < arr[0].length; j++) {
//                if (j == 0) {
//                    System.out.print(arr[i][j]);
//                } else {
//                    System.out.print(" " + arr[i][j]);
//                }
//            }
//            System.out.println();
//        }

        int[][] res = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 1) {
                    res[i][j] = helper(arr, i, j);
                } else {
                    res[i][j] = 0;
                }
            }
        }
        return res;
    }

    class Point {
        int x;
        int y;
        int step;

        public Point(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }

    public int helper(int[][] arr, int i, int j) {
        int[][] mark = new int[arr.length][arr[0].length];

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(i, j, 0));
        while (!queue.isEmpty()) {

            int size = queue.size();
            while (size-- > 0) {
                Point point = queue.poll();

                int x = point.x;
                int y = point.y;
                mark[x][y] = 1;
                if (x - 1 >= 0 && mark[x - 1][y] == 0) {
                    if (arr[x - 1][y] == 0) {
                        return point.step + 1;
                    }
                    queue.add(new Point(x - 1, y, point.step + 1));
                    mark[x - 1][y] = 1;
                }
                if (x + 1 < arr.length && mark[x + 1][y] == 0) {
                    if (arr[x + 1][y] == 0) {
                        return point.step + 1;
                    }
                    queue.add(new Point(x + 1, y, point.step + 1));
                    mark[x + 1][y] = 1;
                }
                if (y - 1 >= 0 && mark[x][y - 1] == 0) {
                    if (arr[x][y - 1] == 0) {
                        return point.step + 1;
                    }
                    queue.add(new Point(x, y - 1, point.step + 1));
                    mark[x][y - 1] = 1;
                }
                if (y + 1 < arr[0].length && mark[x][y + 1] == 0) {
                    if (arr[x][y + 1] == 0) {
                        return point.step + 1;
                    }
                    queue.add(new Point(x, y + 1, point.step + 1));

                    mark[x][y + 1] = 1;
                }
            }

        }
        return -1;

    }

    public static void main(String[] args) {
        WangYi wangYi = new WangYi();
//        System.out.println(main.s2());
//        System.out.println(main.s3());
        int[][] res = wangYi.s4();
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                if (j == 0) {
                    System.out.print(res[i][j]);
                } else {
                    System.out.print(" " + res[i][j]);
                }
            }
            System.out.println();
        }
    }

}
