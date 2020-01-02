import java.util.HashMap;
import java.util.Map;

public class MaxLengthSubString_48 {
    Map<Integer, String> map = new HashMap<>();

    public int maxLengthSubString(String string) {
        if (string == null || string.length() == 0) {
            return 0;

        }
        //初始化长度为26的数组，赋值为-1，表示当前字符没有出现
        int[] pre = new int[26];
        for (int i = 0; i < pre.length; i++) {
            pre[i] = -1;
        }
        int maxLen = 0;
        int curLen = 0;

        for (int i = 0; i < string.length(); i++) {
            int c = string.charAt(i) - 'a';
            int preIndex = pre[c];
            if (preIndex == -1 || i - preIndex > curLen) {
                curLen++;
            } else {
                maxLen = Math.max(curLen, maxLen);
                int d = i - preIndex;
                curLen = d;

            }
            pre[c] = i;
        }
        maxLen= Math.max(curLen,maxLen);
        return maxLen;


    }

    public static void main(String[] args) {
        System.out.println(new MaxLengthSubString_48().maxLengthSubString("arabcacfr"));
    }
}
