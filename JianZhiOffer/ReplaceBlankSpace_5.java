import java.util.ArrayList;

public class ReplaceBlankSpace_5 {
    public static String replaceBlankSpace(StringBuffer str) {
//        return str.toString().replace(" ","%20");
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == ' ') {
                stringBuffer.append("%20");
            } else {
                stringBuffer.append(c);
            }
        }
        return stringBuffer.toString();

    }

    public static String replaceBlankSpace_2(String str) {
        if (str == null) {
            return null;
        }
        char[] chars = str.toCharArray();
        int count = 0;

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                count++;
            }
        }
        int newLength = chars.length + (count << 1);
        int p1 = chars.length - 1;
        int p2 = newLength - 1;
        char[] newChars = new char[newLength];
        while (p1 >= 0) {
            if (chars[p1] == ' ') {
                newChars[p2--] = '0';
                newChars[p2--] = '2';
                newChars[p2--] = '%';
                p1--;
            } else {
                newChars[p2--] = chars[p1--];
            }
        }

        return String.valueOf(newChars);

    }

    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer("we are happy");
        System.out.println(replaceBlankSpace(stringBuffer));
        String str = "we are happy";
        System.out.println(replaceBlankSpace_2(str));

    }

}
