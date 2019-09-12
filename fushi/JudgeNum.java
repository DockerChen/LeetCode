import java.util.Scanner;

public class JudgeNum {
    public static boolean judgeNum(int num) {
        String str = String.valueOf(num);
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                return false;
            }
        }
        return true;


    }

    public static void main(String[] args) {
        int n;
        int num;
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        if (n == 0) {

        } else {
            while (n-- >= 0) {
                num = scan.nextInt();
                if (judgeNum(num)) {
                    System.out.println("true");
                } else {
                    System.out.println("false");
                }
            }
        }
    }
}
