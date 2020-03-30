import java.util.*;

public class Microsoft {

    int ans = Integer.MAX_VALUE;
    Path minPath = new Path(new ArrayList<>(), Integer.MAX_VALUE);

    public static void main(String[] args) {
//        Microsoft microsoft = new Microsoft();
//        int[] arr = {1, 4, 3, 1, 5};
////        System.out.println(minimumMoves(arr));
//        int[] arr1 = {1, 1};
////        System.out.println(minimumMoves(arr1));
//        int city = 5;
//        int road = 5;
//        String[] strs = {"None", "Cop", "None", "None", "None"};
//        int[][] map = {{0, 1}, {0, 2}, {1, 2}, {2, 3}, {3, 4}};
//
//        int city1 = 7;
//        int road1 = 8;
//        String[] strs1 = {"None", "Cop", "Sand", "None", "Nitro", "None", "None"};
//        int[][] map1 = {{0, 1}, {0, 2}, {1, 2}, {2, 3}, {2, 4}, {3, 6}, {4, 5}, {5, 6}};
//
//        int[] res = microsoft.minTimes(city1, strs1, road1, map1);
//        for (int i = 0; i < res.length; i++) {
//            System.out.print(res[i] + " ");
//        }
//        System.out.println();
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        Path p1 = microsoft.new Path(list, 111);
//        System.out.println("p1" + p1.path.toString() + " " + p1.cost);
//        Path p2 = microsoft.new Path(new ArrayList<>(p1.path), 222);
//        list.remove(Integer.valueOf(1));
//
//        System.out.println("p1" + p1.path.toString() + " " + p1.cost);
//        System.out.println("p2" + p2.path.toString() + " " + p2.cost);
        Microsoft test = new Microsoft();
        int N1 = 12;
        int K1 = 4;
        int[] arr1 = {3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 10, 10};
        int N = 6;
        int K = 2;
        int[] arr = {1, 2, 3, 3, 5, 10};
        System.out.println(test.s1_1(N, K, arr));
        test.ans = Integer.MAX_VALUE;
        System.out.println(test.s1_1(N1, K1, arr1));

    }

    public int s1_1(int N, int K, int[] arr) {
        Arrays.sort(arr);
        Map<Integer, Integer> map = new HashMap<>();
        int dup_max = 0;
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
            dup_max = Math.max(dup_max, map.get(arr[i]));
        }
        //重复数字出现最多的个数，如果大于N/K的话，不能划分，直接返回0
        if (dup_max > N / K) {
            return 0;
        }
        int[] num = new int[N];
        //用于判断该位置是否放了数字
        int[] flag = new int[N];
        helper(N, K, 0, arr, num, flag);
        return ans;

    }

    //dfs + 剪枝
    public void helper(int N, int K, int index, int[] arr, int[] num, int[] flag) {
        //index==N,说明分组完毕，计算每个子数组最大最小元素的差值之和
        if (index == N) {
            int res = 0;
            for (int i = 0; i < num.length; ) {
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                for (int j = i; j < i + K; j++) {
                    min = Math.min(min, num[j]);
                    max = Math.max(max, num[j]);
                }
                res += Math.abs(max - min);
                i += K;
            }
            if (res < ans) {
                ans = res;
                for (int i = 0; i < num.length; i++) {
                    System.out.print(num[i] + " ");
                }
                System.out.println();
                System.out.println(res);
            }

            return;
        }

        for (int i = 0; i < N; i++) {
            //剪枝
            if (flag[i] == 0 && (index == 0 || (index != 0 && arr[i] != num[index - 1]))) {
                flag[i] = 1;
                num[index] = arr[i];
                if (index >= 0 && index <= N - 1) {
                    //判断分组中是否存在重复元素
                    for (int m = 0; m < num.length && m <= index; ) {
                        Set<Integer> set = new HashSet<>();
                        for (int j = m; j < m + K && j <= index; j++) {
                            //分组中存在重复元素，直接返回
                            if (set.contains(num[j])) {
                                flag[i] = 0;
                                num[index] = 0;
                                return;
                            } else {
                                set.add(num[j]);
                            }
                        }
                        m += K;
                    }
                }
                helper(N, K, index + 1, arr, num, flag);
                flag[i] = 0;
                num[index] = 0;
            }
        }
    }

    //动态规划
    public int minimumMoves(int[] arr) {
        int n = arr.length;
        //dp[i][j]表示删除从i到j的数字所需的最少操作次数
        int[][] dp = new int[n + 1][n + 1];
        //l表示当前数字的长度
        for (int l = 1; l <= n; l++) {
            int i = 0, j = l - 1;
            while (j < n) {
                if (l == 1) {
                    //base，每个数字的删除次数为1
                    dp[i][j] = 1;
                } else {
                    //不考虑回文子串的情况下，删除次数为之前的删除次数+1
                    dp[i][j] = 1 + dp[i + 1][j];
                    //考虑回文子串
                    for (int k = i + 1; k <= j; k++) {
                        if (arr[i] == arr[k]) {
                            //更新dp[i][j]
                            dp[i][j] = Math.min(dp[i][j], dp[i + 1][k - 1] + dp[k + 1][j] + (i + 1 == k ? 1 : 0));
                        }
                    }
                }
                i++;
                j++;
            }
        }
        return dp[0][n - 1];

    }

    public int[] minTimes(int city, String[] strs, int road, int[][] arr) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            Set<Integer> set;
            if (!map.containsKey(arr[i][0])) {
                set = new HashSet<>();
            } else {
                set = map.get(arr[i][0]);
            }
            set.add(arr[i][1]);
            map.put(arr[i][0], set);
        }
        Path path = new Path(new ArrayList<>(), 0);

        dfs(0, city - 1, strs, map, path);

        int[] res = new int[minPath.path.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = minPath.path.get(i);
        }
        return res;

    }

    //计算起点到终点的花费
    public double cal(String[] strs, Path path) {
        double res = 0;
        int size = path.path.size();
        double[] cost = new double[size - 1];
        Arrays.fill(cost, 1);
        for (int i = 0; i < size; i++) {
            if (strs[path.path.get(i)].equals("Nitro")) {
                if (i < size - 1) {
                    cost[i] *= 0.5;
                }
                if (i + 1 < size - 1) {
                    cost[i + 1] *= 0.5;
                }

            } else if (strs[path.path.get(i)].equals("Sand")) {
                if (i < size - 1) {
                    cost[i] *= 2;
                }
                if (i + 1 < size - 1) {
                    cost[i + 1] *= 2;
                }

            } else if (strs[path.path.get(i)].equals("Crash")) {
                if (i < size - 1) {
                    cost[i] += 1;
                }
            }

        }

        for (int i = 0; i < cost.length; i++) {
            res += cost[i];
        }
        return res;

    }

    //图的深度优先遍历
    public void dfs(int begin, int end, String[] strs, Map<Integer, Set<Integer>> map, Path path) {
        if (begin == end) {
            path.path.add(end);
            double cost = cal(strs, path);
            if (cost < minPath.cost) {
                // 一定要使用new ArrayList<>(path.path)。
                // 直接传入path.path，会导致返回的minPath.path为空，这是由于Java的值传递导致的。
                minPath = new Path(new ArrayList<>(path.path), cost);
//                System.out.println("cost:" + cost);
//                System.out.println("minPath" + minPath.path.toString());
            }
            //回溯
            path.path.remove(Integer.valueOf(end));

            return;
        }

        Set<Integer> set = map.get(begin);
        //寻找下一个可访问的节点
        for (int nextCity : set) {
            if (path.path.contains(nextCity) || strs[nextCity].equals("Cop")) {
                continue;
            }
            path.path.add(begin);
            dfs(nextCity, end, strs, map, path);
            //回溯
            path.path.remove(Integer.valueOf(begin));

        }

    }

    class Path {
        List<Integer> path;
        double cost;

        public Path(List<Integer> path, double cost) {
            this.path = path;
            this.cost = cost;
        }

    }
}

