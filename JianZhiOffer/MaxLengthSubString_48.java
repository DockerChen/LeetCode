public class MaxLengthSubString_48 {


    public int maxLengthSubString(String s) {
        if (s == null || s.length() == 0) {
            return 0;

        }
        //int [26] 用于字母 ‘a’ - ‘z’ 或 ‘A’ - ‘Z’
        //int [128] 用于 ASCII 码
        //int [256] 用于扩展 ASCII 码
        //ASCII码128位,初始化长度为128的数组，赋值为-1，表示当前字符没有出现
        int[] pre = new int[128];
        for (int i = 0; i < pre.length; i++) {
            pre[i] = -1;
        }
        int maxLen = 0;
        int curLen = 0;


        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i);
            int preIndex = pre[c];
            if (preIndex == -1 || i - preIndex > curLen) {
                curLen++;
                maxLen = Math.max(curLen, maxLen);
            } else {
                maxLen = Math.max(curLen, maxLen);
                int d = i - preIndex;
                curLen = d;

            }
            pre[c] = i;
        }

        return maxLen;


    }

    public static void main(String[] args) {
        System.out.println(new MaxLengthSubString_48().maxLengthSubString("arabcacfr"));
    }
}
