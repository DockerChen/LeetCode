import java.math.BigInteger;
import java.util.Scanner;

public class Solution1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String n = sc.next();
        BigInteger in = BigInteger.valueOf(Long.parseLong(n));
        BigInteger num = f(in);//种数
        BigInteger x = BigInteger.valueOf(10).pow(9).add(BigInteger.valueOf(7));
        System.out.println(num.mod(x));
    }

    private static BigInteger f(BigInteger n) {
        return n.multiply(BigInteger.valueOf(2).pow(n.intValue() - 1));
    }
}