import java.util.ArrayList;

public class SumToS_57_1 {
    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        int left = 0;
        int right = array.length - 1;
        int min = Integer.MAX_VALUE;
        int res1 = 0;
        int res2 = 0;
        ArrayList arrayList = new ArrayList();

        while (left < right) {
            int curSum = array[left] + array[right];
            if (curSum == sum) {
                if (curSum < min) {
                    res1 = array[left];
                    res2 = array[right];
                    min = array[left] * array[right];
                }
                left++;
                right--;

            } else if (curSum > sum) {
                right--;
            } else {
                left++;
            }


        }

        if (min != Integer.MAX_VALUE) {
            arrayList.add(res1);
            arrayList.add(res2);
        }
        return arrayList;

    }

    public static void main(String[] args) {
        SumToS_57_1 test = new SumToS_57_1();
        int[] array = {1, 2, 3, 4, 5};
        ArrayList<Integer> arrayList = test.FindNumbersWithSum(array, 10);
        System.out.println(arrayList.toString());
    }
}
