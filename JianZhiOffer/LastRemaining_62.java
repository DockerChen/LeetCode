public class LastRemaining_62 {
    //使用数组来模拟这个过程，这可以使用链表来模拟
    public int LastRemaining_Solution(int n, int m) {
        if (n == 0 || m == 0) {
            return -1;
        }

        int[] arr = new int[n];
        int count = n;
        int index = 0;
        while (count != 1) {
            //找到第m个数
            for (int i = 1; i < m; index++) {
                if (index == n) {
                    index = 0;
                }
                while (arr[index] == 1) {
                    index++;
                    if (index == n) {
                        index = 0;
                    }
                }
                i++;
            }

            if (index == n) {
                index = 0;
            }
            for (int i = index; i <= n; i++) {
                if (i == n) {
                    i = 0;
                }
                if (arr[i] == 0) {
                    arr[i] = 1;
                    index = i;
                    break;
                }
            }

            count--;
//            System.out.print(index + " ");

        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            if (arr[i] != 1) {
                return i;
            }
        }
        return -1;

    }
    // 约瑟夫环问题
    // 递推公式:f(n,m)=(f(n-1,m)+m)%n
    // 理解这个递推式的核心在于关注胜利者的下标位置是怎么变的。每杀掉一个人，其实就是把这个数组向前移动了 m 位。然后逆过来，就可以得到这个递推式。
    public int LastRemaining_Solution_2(int n, int m) {
        if (n == 0 || m == 0) {
            return -1;
        }
        if (n == 1) {
            return 0;
        }
        return (LastRemaining_Solution_2(n - 1, m) + m) % n;

    }

    public static void main(String[] args) {
        LastRemaining_62 test = new LastRemaining_62();
        System.out.println(test.LastRemaining_Solution(5, 3));

    }
}
