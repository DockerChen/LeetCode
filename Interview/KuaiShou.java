import java.util.*;

public class KuaiShou {
    List<Coordinate> ans = new ArrayList<>();

    public static long judge(int[][] power, int x, int y, int a, int b) {
        int min = Integer.MAX_VALUE;
        for (int i = x; i < x + a; i++) {
            for (int j = y; j < y + b; j++) {
                if (power[i][j] < 0) {
                    return Integer.MAX_VALUE;
                } else if (power[i][j] < min) {
                    min = power[i][j];
                }
            }

        }

        long ans = 0;
        for (int i = x; i < x + a; i++) {
            for (int j = y; j < y + b; j++) {
                ans += power[i][j] - min;
            }

        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int[][] power = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                power[i][j] = scanner.nextInt();
            }
        }

        KuaiShou main = new KuaiShou();
        main.helper(a, b, power);
        System.out.println(main.ans.size());
        for (int i = 0; i < main.ans.size(); i++) {
            String s = (main.ans.get(i).x + 1) + " " + (main.ans.get(i).y + 1) + " " + main.ans.get(i).power;
            System.out.println(s);

        }

        /*
        4 5 2 3
        1 8 7 9 4
        7 5 4 2 2
        6 7 8 9 3
        4 5 5 6 2

        * */

    }

    /**
     * 获取队中从前到后每个人与前方身高高于自己的人的最短距离
     *
     * @param height int整型一维数组 队中从前到后每个人与前方身高高于自己的人的最短距离
     * @return int整型一维数组
     */
    public int[] DistanceToHigher(int[] height) {
        // write code here
        int[] res = new int[height.length];
        Arrays.fill(res, Integer.MAX_VALUE);
        for (int i = 0; i < height.length; i++) {
            for (int j = 0; j < i; j++) {
                if (height[j] > height[i]) {
                    int dis = i - j;
                    res[i] = Math.min(dis, res[i]);
                }
            }
        }
        for (int i = 0; i < res.length; i++) {
            if (res[i] == Integer.MAX_VALUE) {
                res[i] = 0;
            }
        }
        return res;
    }

    public void find() {
        String str;
        Scanner scanner = new Scanner(System.in);
        str = scanner.nextLine();
        if (str == null) {
            System.out.println(-1);
            return;
        }
        String[] strings = str.split(" ");
        int[] arr = new int[strings.length];
        if (arr.length == 0) {
            System.out.println(-1);
            return;
        }

        boolean flag = false;
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(strings[i]);
        }

        for (int i = 0; i < arr.length; i++) {

            int count = 0;
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[i]) {
                    count++;
                }
            }
            if (count == 1) {
                flag = true;
                res.add(i);
            }

        }

        if (!flag) {
            System.out.println(-1);

        } else {
            for (int i = 0; i < res.size(); i++) {
                if (i != res.size() - 1) {
                    System.out.print(res.get(i) + " ");
                } else {
                    System.out.print(res.get(i));

                }
            }
            System.out.println();
        }
    }

    public void getRes() {
        String str;
        Scanner scanner = new Scanner(System.in);
        str = scanner.nextLine();

        String[] strings = str.split(",");
        if (strings.length == 0) {
            System.out.println("null");
            return;
        }

        ArrayList<String> res = new ArrayList<>();
        for (String string : strings) {
            int tmp1 = help1(string);
            int tmp2 = help2(string);
            if (tmp1 != 0 || tmp2 != 0) {
                String ss = tmp1 > tmp2 ? string + "1" + tmp1 : string + "2" + tmp2;
                res.add(ss);
            }
        }

        res.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.charAt(12) != o2.charAt(12)) {
                    return o2.charAt(12) - o1.charAt(12);
                } else {
                    return o2.charAt(11) - o1.charAt(11);
                }
            }
        });
        if (res.size() == 0) {
            System.out.println("null");
        } else {
            for (int i = 0; i < res.size(); i++) {
                if (i != res.size() - 1) {
                    System.out.print(res.get(i).substring(0, 11) + ",");
                } else {
                    System.out.print(res.get(i).substring(0, 11));
                }

            }
            System.out.println();
        }

    }

    //顺子判断
    public int help1(String str) {
        int max = 0;
        for (int i = 4; i < str.length(); i++) {
            int tmp = 1;
            while (i < str.length() && str.charAt(i) - str.charAt(i - 1) == 1) {
                i++;
                tmp++;
            }
            max = Math.max(max, tmp);
        }
        return max >= 3 ? max : 0;
    }

    //豹子判断
    public int help2(String str) {
        int max = 0;
        for (int i = 4; i < str.length(); i++) {
            int tmp = 1;
            while (i < str.length() && str.charAt(i) == str.charAt(i - 1)) {
                i++;
                tmp++;
            }
            max = Math.max(max, tmp);
        }
        return max >= 3 ? max : 0;
    }

    public void helper(int a, int b, int[][] power) {
        Coordinate coordinate = new Coordinate(0, 0, Integer.MAX_VALUE);
        for (int i = 0; i <= power.length - a; i++) {
            for (int j = 0; j <= power[0].length - b; j++) {
                long temp = judge(power, i, j, a, b);

                if (temp != Integer.MAX_VALUE && temp < coordinate.power) {
                    coordinate = new Coordinate(i, j, temp);
                }

            }
        }

        if (coordinate.power != Integer.MAX_VALUE) {
            for (int i = coordinate.x; i < coordinate.x + a; i++) {
                for (int j = coordinate.y; j < coordinate.y + b; j++) {
                    power[i][j] = -1;
                }

            }
            ans.add(coordinate);
            helper(a, b, power);
        }

    }

    class Coordinate {
        int x;
        int y;
        long power;

        public Coordinate(int x, int y, long power) {
            this.x = x;
            this.y = y;
            this.power = power;
        }
    }
}