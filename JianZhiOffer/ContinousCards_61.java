import java.util.Arrays;

public class ContinousCards_61 {
    public boolean isContinuous(int[] numbers) {
        if (numbers.length < 5) {
            return false;
        }
        Arrays.sort(numbers);
        //统计0的个数
        int countZero = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 0)
                countZero++;
        }

        for (int i = countZero; i < numbers.length - 1; i++) {
            //判断对子
            if (numbers[i] == numbers[i + 1]) {
                return false;
            }
            //用0填充缺失的数字
            countZero = countZero - (numbers[i + 1] - numbers[i] - 1);
        }
        //最后只需判断0的个数是否大于等于0，小于0则说明缺失的数字用0去填补（或者数组中没有0）是不够的，因此是不连续的.
        return countZero >= 0;


    }
}
