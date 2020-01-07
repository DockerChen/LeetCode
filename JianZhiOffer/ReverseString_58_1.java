public class ReverseString_58_1 {
    public String ReverseSentence(String str) {

        if (str != null && str.length() != 0) {
            char[] chars = str.toCharArray();
            reverse(chars, 0, chars.length - 1);
            int begin = 0;
            int end = 0;
            while (begin < chars.length) {
                //划分单词，对每一个单词进行翻转
                if (chars[end] == ' ' || end == chars.length - 1) {
                    int tmp_end = end;
                    if (chars[end] == ' ') {
                        tmp_end = end - 1;
                    }
                    reverse(chars, begin, tmp_end);
                    end++;
                    begin = end;
                } else {
                    end++;

                }
            }

            return String.valueOf(chars);
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
        ReverseString_58_1 test = new ReverseString_58_1();
        System.out.println(test.ReverseSentence("I am a student."));
//        String str=null;
//        String str1="";
//        System.out.println(str.length());
//        System.out.println(str1.length());
    }

}
