import java.lang.reflect.Array;

public class MaxSum_42 {
    public int FindGreatestSumOfSubArray(int[] arr) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {

            if (sum < 0) {
                max = arr[i] > max ? arr[i] : max;
                sum = arr[i];

            } else {
                sum += arr[i];
                max = sum > max ? sum : max;
            }
        }

        return max;

    }
    //动态规划
    public int FindGreatestSumOfSubArray_2(int[] array) {
        int[] s = array.clone();

        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            if (sum < 0) {
                s[i] = array[i] > s[i] ? array[i] : s[i];
                sum = array[i];

            } else {
                sum += array[i];
                s[i] = sum > s[i] ? sum : s[i];
            }
        }
        int max = s[0];

        for (int i = 0; i < s.length; i++) {
            max = s[i] > max ? s[i] : max;
        }

        return max;

    }

    public static void main(String[] args) {
        MaxSum_42 maxSum_42 = new MaxSum_42();
        int[] arr = {6, -3, -2, 7, -15, 1, 2, 2};
        System.out.println(maxSum_42.FindGreatestSumOfSubArray(arr));
    }
}
