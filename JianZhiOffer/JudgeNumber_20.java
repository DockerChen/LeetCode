import java.util.ArrayList;
import java.util.List;

public class JudgeNumber_20 {
    public boolean judge(String str) {
        if (str == null || str.length() == 0)
            return false;
        return str.matches("[+-]?\\d*(\\.\\d+)?([eE][+-]?\\d+)?");

    }

    public static void main(String[] args) {
        JudgeNumber_20 judgeNumber_20 = new JudgeNumber_20();
        String[] strings = {"+100", "5e2", "-123", "3.1416", "-1E-16",
                "12e", "1a3.14", "1.2.3", "+-5", "12e+4.3" };
        for (String str : strings) {
            System.out.println(str + " " + judgeNumber_20.judge(str));
        }

        char[] c=".*".toCharArray();
        String str ="a";
        int res=str.compareTo("a");

        System.out.println(c.length);
        System.out.println(res);
        System.out.println(str.equals("a"));
        System.out.println(String.valueOf(c));

    }



}
