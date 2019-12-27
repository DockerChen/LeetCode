import com.sun.xml.internal.ws.message.source.PayloadSourceMessage;
import org.omg.CORBA.FREE_MEM;

import javax.swing.*;

public class Frequency_43 {
    public int NumberOf1Between1AndN_Solution(int n) {
        long sum = 0;
        long num = 1;
        long preNumber = 0;
        long curNumber = 0;
        long sufNumber = 0;
        while (num <= n) {
            sufNumber = n % num;
            curNumber = n % (num * 10) / num;
            preNumber = n / (num * 10);
            if (curNumber > 1) {
                System.out.println("1--- "+sum+"+"+num * (preNumber + 1));
                sum += num * (preNumber + 1);
                System.out.println("1=== "+sum);

            } else if (curNumber == 1) {
                System.out.println("1--- "+sum+"+"+(num * preNumber + sufNumber + 1));
                sum += num * preNumber + sufNumber + 1;
                System.out.println("1=== "+sum);
            } else {
                System.out.println("1--- "+sum+"+"+num*preNumber);
                sum += num * preNumber;
                System.out.println("1=== "+sum);
            }
            num = num * 10;

            System.out.println("1---num=== "+num);

        }
        int res=(int)sum;
        return res;

    }

    public int NumberOf1Between1AndN_Solution_2(int n) {
        int sum = 0;
        int num = 1;
        int preNumber = 0;
        int curNumber = 0;
        int sufNumber = 0;
        while (num <= n) {
            sufNumber = n % num;
            curNumber = n % (num * 10) / num;
            preNumber = n / (num * 10);
            if (curNumber > 1) {
                System.out.println("2--- "+sum+"+"+(num * (preNumber + 1)));
                sum += num * (preNumber + 1);
                System.out.println("2=== "+sum);
            } else if (curNumber == 1) {
                System.out.println("2--- "+sum+"+"+(num * preNumber + sufNumber + 1));
                sum += num * preNumber + sufNumber + 1;
                System.out.println("2=== "+sum);
            } else {
                System.out.println("2--- "+sum+"+"+(num * preNumber));
                sum += num * preNumber;
                System.out.println("2=== "+sum);
            }
            num = num * 10;
            System.out.println("2---num=== "+num);

        }

        return sum;

    }


    public int method2(int n) {

        long cnt = 0;
        for (long m = 1; m <= n; m *= 10) {
            long a = n / m, b = n % m;
            cnt += (a + 8) / 10 * m + (a % 10 == 1 ? b + 1 : 0);
        }

        int res=(int)cnt;

        return res;

    }

    public static void main(String[] args) {
        Frequency_43 frequency_43 = new Frequency_43();
        System.out.println(frequency_43.NumberOf1Between1AndN_Solution(1410065408));
        System.out.println(frequency_43.NumberOf1Between1AndN_Solution_2(1410065408));
//        System.out.println(frequency_43.method2(100000));
        int a=1327102090;
        int b=a+(1737167499-a);
        System.out.println(b);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);


        /*
         * 1410065408
         * 1737167499
         * 1327102090
         *
         * 410065409
         * 1000000000
         *
         * 1967865206
         * 4294967296
         * 2147483647
         * 9223372036854775807
         * */
    }

}
