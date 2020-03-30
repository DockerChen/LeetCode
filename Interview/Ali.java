import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Ali {
    //用大数表示
    public static void s1() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        BigInteger res = BigInteger.valueOf(n).multiply(BigInteger.valueOf(2).pow(n - 1));
        BigInteger x = BigInteger.valueOf(10).pow(9).add(BigInteger.valueOf(7));
        BigInteger ans = res.mod(x);
        System.out.println(ans);
    }

    public static void s1_1() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long a = fastPower(10, 9) + 7;
        long res = (n * fastPower(2, n - 1, a)) % a;
        System.out.println(res);
    }

    //快速幂
    public static long fastPower(int n, int m, long a) {
        long res = 1;
        long base = n;
        while (m != 0) {
            if ((m & 1) == 1) {
                res = (res * base) % a;
            }
            //为了避免超出long的范围，所以取三次模
            base = (base % a) * (base % a) % a;
            m = m >> 1;
        }
        return res;
    }

    public static long fastPower(int n, int m) {
        long res = 1;
        long base = n;
        while (m != 0) {
            if ((m & 1) == 1) {
                res = res * base;
            }
            base = base * base;
            m = m >> 1;
        }
        return res;
    }

    public static void s2() {
        int n, m;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        char[][] chars = new char[n][m];
        for (int i = 0; i < n; i++) {
            String str = scanner.next();
            for (int j = 0; j < m; j++) {
                chars[i][j] = str.charAt(j);
            }
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (chars[i][j] == 'S') {
                    res = dfs(chars, i, j);
//                    System.out.println(i+" "+j);
                    break;
                }
            }
        }

        System.out.println(res);
    }

    public static int dfs(char[][] chars, int i, int j) {
        Queue<Point> queue = new LinkedList<>();
        int[][] visit = new int[chars.length][chars[0].length];
        int[][] a = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Point point = new Point(i, j, 0, 5);
        visit[i][j] = 1;
        queue.add(point);
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            if (chars[cur.x][cur.y] == 'E') {
//                System.out.println(cur.x + " " + cur.y);
                return cur.step;
            }
            Point nxt;
            //上下左右
            for (int k = 0; k < 4; k++) {
                nxt = new Point(cur.x + a[k][0], cur.y + a[k][1], cur.step + 1, cur.count);
                if (check(nxt.x, nxt.y, chars) && visit[nxt.x][nxt.y] == 0 && (chars[nxt.x][nxt.y] == 'E' || chars[nxt.x][nxt.y] == '.')) {
                    visit[nxt.x][nxt.y] = 1;
                    queue.add(nxt);
                }

            }

            //飞行器,最多使用5次
            if (cur.count >= 1) {
                int n = chars.length;
                int m = chars[0].length;
                nxt = new Point(n - 1 - cur.x, m - 1 - cur.y, cur.step + 1, cur.count - 1);
                if (check(nxt.x, nxt.y, chars) && visit[nxt.x][nxt.y] == 0 && (chars[nxt.x][nxt.y] == 'E' || chars[nxt.x][nxt.y] == '.')) {
                    visit[nxt.x][nxt.y] = 1;
                    queue.add(nxt);
                }
            }

        }
        return -1;

    }

    private static boolean check(int x, int y, char[][] chars) {
        if (x < 0 || x >= chars.length || y < 0 || y >= chars[0].length) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
//        s1();
//        s1_1();
        s2();

    }

    static class Point {
        int x, y;
        int step;
        int count;

        public Point(int x, int y, int step, int count) {
            this.x = x;
            this.y = y;
            this.step = step;
            this.count = count;
        }
    }
}
