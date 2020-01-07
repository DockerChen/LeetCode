public class RotateLeft_58_2 {
    public String LeftRotateString(String str, int n) {
        //边界条件判断
        if (str != null && str.length() != 0) {
            if (n > 0 && n < str.length()) {
                char[] chars = str.toCharArray();
                reverse(chars, 0, n - 1);
                reverse(chars, n, chars.length - 1);
                reverse(chars, 0, chars.length - 1);
                return String.valueOf(chars);
            }
        }
        return str;


    }

    // 翻转字符串
    public void reverse(char[] chars, int begin, int end) {

        while (begin < end) {
            char tmp = chars[begin];
            chars[begin] = chars[end];
            chars[end] = tmp;
            begin++;
            end--;
        }
    }

    public static void main(String[] args) {
        RotateLeft_58_2 test = new RotateLeft_58_2();
        System.out.println(test.LeftRotateString("abcdefg", 2));
    }


}
