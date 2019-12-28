import java.util.Arrays;
import java.util.Comparator;

public class MinNumber_45 {
    public String PrintMinNumber(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return "";
        }
        String[] strings = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            strings[i] = numbers[i] + "";
        }

        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1 + o2).compareTo(o2 + o1);
            }
        });

        /*lambda表达式
        Arrays.sort(strings, (a, b) -> (a + b).compareTo(b + a));*/

        String res = "";
        for (int i = 0; i < strings.length; i++) {
            res += strings[i];
        }

        return res;

    }
}
