public class Add_65 {
    /*
    链接：https://www.nowcoder.com/questionTerminal/59ac416b4b944300b617d4f7f111b215?f=discussion
来源：牛客网

首先看十进制是如何做的： 5+7=12，三步走
第一步：相加各位的值，不算进位，得到2。
第二步：计算进位值，得到10. 如果这一步的进位值为0，那么第一步得到的值就是最终结果。

第三步：重复上述两步，只是相加的值变成上述两步的得到的结果2和10，得到12。

同样我们可以用三步走的方式计算二进制值相加： 5-101，7-111 第一步：相加各位的值，不算进位，得到010，二进制每位相加就相当于各位做异或操作，101^111。

第二步：计算进位值，得到1010，相当于各位做与操作得到101，再向左移一位得到1010，(101&111)<<1。

第三步重复上述两步， 各位相加 010^1010=1000，进位值为100=(010&1010)<<1。
     继续重复上述两步：1000^100 = 1100，进位值为0，跳出循环，1100为最终结果。
     */
    public int Add(int num1, int num2) {
        int res=num1^num2;
        int flag=(num1&num2)<<1;
        int tmp;
        while(flag!=0){
            tmp=res;
            res=tmp^flag;
            flag=(tmp&flag)<<1;
        }
        return res;
    }

    //递归
    public int Add_1(int a, int b) {
        return b == 0 ? a : Add(a ^ b, (a & b) << 1);
    }

    public static void main(String[] args) {
        Add_65 test =new Add_65();
        System.out.println(test.Add_1(111,899));
    }
}
