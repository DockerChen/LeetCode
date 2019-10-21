public class BinaryNumber_15 {
    public int NumberOf1(int n) {
        //数字在计算机中以二进制形式存储，负数在计算机中以补码存储，int类型的数据占4个字节
        //为了防止负数右移出现死循环的情况，可以把1每次左移一位，然后和n比较
        int res = 0;
        int flag = 1;
        while (flag != 0) {
            if ((n & flag) != 0) {
                res++;
            }
            flag = flag << 1;
        }
        return res;
    }

    public int NumberOf1Improve(int n) {
        //(n-1)&n 每次运算的结果将n中二进制表示最右边的1变为0
        int res = 0;
        while (n != 0) {
            n=(n-1)&n;
            res++;
        }
        return res;
    }

    public static void main(String[] args) {
        BinaryNumber_15 binaryNumber_15 = new BinaryNumber_15();
        int res = binaryNumber_15.NumberOf1(-8);
        System.out.println(res);

    }
}
