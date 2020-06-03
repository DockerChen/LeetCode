import java.util.*;

public class MicroExercise {
    public void rotate(int[][] matrix) {
        int temp = -1;
        for (int start = 0, end = matrix.length - 1; start < end; start++, end--) {
            for (int s = start, e = end; s < end; s++, e--) {
                temp = matrix[start][s];
                matrix[start][s] = matrix[e][start];
                matrix[e][start] = matrix[end][e];
                matrix[end][e] = matrix[s][end];
                matrix[s][end] = temp;

            }

        }

    }

    static int ans = 0;
    static int n, k, m;
    static int[][] graph;
    static int[] color;

    public static void colorSum() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        k = scanner.nextInt();
        m = scanner.nextInt();
        graph = new int[n + 1][n + 1];
        color = new int[n + 1];
        for (int i = 1; i <= k; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            graph[a][b] = graph[b][a] = 1;
        }
        dfs(1);
        System.out.println(ans);
    }

    public static void dfs(int index) {
        if (index == n + 1) {
            ++ans;
            return;
        }
        for (int i = 1; i <= m; i++) {
            color[index] = i;
            if (helper(index) == 1) {
                dfs(index + 1);
            }
            color[index] = 0;
        }

    }

    public static int helper(int x) {
        for (int i = 1; i <= n; i++) {
            if (graph[x][i] == 1 && color[x] == color[i]) {
                return 0;
            }
        }
        return 1;
    }

    public String maxSubStr(String str) {
        int len = str.length();
        int[] dp = new int[len + 1];
        dp[1] = str.charAt(0) - '0' == 0 ? -1 : 1;
        for (int i = 2; i <= len; i++) {
            dp[i] = str.charAt(i - 1) - '0' == 0 ? -1 : 1;
            dp[i] += dp[i - 1];
        }
        int begin, max = 0, start = 0, end = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= len; i++) {
            begin = map.getOrDefault(dp[i], 0);
            if (begin == 0 && dp[i] != 0) {
                map.put(dp[i], i);
            } else {
                if (i - begin > max) {
                    max = i - begin;
                    start = begin;
                    end = i;
                }
            }
        }
        System.out.println(max);
        System.out.println(start + "," + end);
        return str.substring(start, end);

    }

    public void calNum(int n) {
        int[] arr = new int[n + 1];
        arr[1] = 0;
        for (int i = 2; i <= n; i++) {
            arr[i] = help(i);
        }

        for (int i = 2; i <= n; i++) {
            if (arr[i] > n) {
                continue;
            }
            if (arr[i] != i && arr[arr[i]] == i) {
                System.out.println(i + "," + arr[i]);
                arr[i] = 1;
            }
        }

    }

    public int help(int num) {
        int sum = 1;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                sum += i;
                sum += num / i;
            }
        }
        return sum;
    }

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        List<Integer> track = new ArrayList<>();
        helper(track, nums);
        return res;

    }

    public void helper(List<Integer> track, int[] nums) {
        if (track.size() == nums.length) {
            res.add(new ArrayList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (track.contains(nums[i])) {
                continue;
            }
            track.add(nums[i]);
            helper(track, nums);
            track.remove(track.size() - 1);
        }

    }

    public int minHuiWen(String str) {
        int len = str.length();
        int[][] dp = new int[len + 1][len + 1];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    System.out.println(i + " " + j);
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i + 1][j]) + 1;
                }

            }

        }
        return dp[0][len - 1];
    }

    public int compare(String a, String b) {
        int len1 = a.length();
        int len2 = b.length();
        int len = Math.min(len1, len2);
        int num1 = 0, num2 = 0;
        for (int i = 0; i < len; i++) {
            if (!isDigit(a.charAt(i)) && !isDigit(b.charAt(i))) {
                if (a.charAt(i) < b.charAt(i)) {
                    return -1;
                } else if (a.charAt(i) > b.charAt(i)) {
                    return 1;
                }
            }

            if (isDigit(a.charAt(i)) || isDigit(b.charAt(i))) {
                int index1 = i;
                int index2 = i;
                while (index1 < len1 && isDigit(a.charAt(index1))) {
                    num1 = num1 * 10 + (a.charAt(index1) - '0');
                    ++index1;
                }
                while (index2 < len2 && isDigit(b.charAt(index2))) {
                    num2 = num2 * 10 + (b.charAt(index2) - '0');
                    ++index2;
                }
                if (num1 < num2) {
                    return -1;
                } else if (num1 > num2) {
                    return 1;
                } else {
                    i = index1;
                }

            }

        }

        if (len1 == len2) {
            return 0;
        } else if (len1 < len2) {
            return -1;
        } else {
            return 1;
        }
    }

    public boolean isDigit(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        return false;
    }

//        a2<a10
//        a1c<a13
//        ab13b<ab14a

    // a<b return -1; a==b return 0; a>b return 1;

    public static void main(String[] args) {
//        colorSum();
        MicroExercise test = new MicroExercise();

        String a = "a11c";
        String b = "a1a";
        System.out.println(test.compare(a, b));

    }
}
