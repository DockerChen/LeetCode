public class AppearOnceInArray_56_1 {
    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
/*
        位运算中异或的性质：两个相同数字异或为0，一个数和 0 异或还是它本身。
         因此，array数组中数字异或的结果二进制中的1，表示的是两个只出现一次数字的不同的位。接下来，以结果中最右边的1所在的位数，来分组，
         从而，可以将两个不同的数字划分到不同的分组中。
         例如，{2,4,3,6,3,2,5,5}，异或的结果是0010，以最右边1所在的位置划分的结果是{2,3,6,3,2}和{4,5,5}。
         diff保存的是array数组中数字异或的结果.
*/
        int diff = 0;
        for (int num : array) {
            diff ^= num;
        }

        //得到diff最右侧1的位置
        diff = diff & (-diff);
        //划分数组成两部分
        for (int num : array) {
            if ((num & diff) == 0)
                num1[0] ^= num;
            else
                num2[0] ^= num;
        }


    }
}
