public class AppearOnceInArray_56_2 {

    public static int FindNumsAppearOnce(int[] array) throws Exception {
        if (array == null || array.length == 0) {
            throw new Exception("input error！");

        }
        //bitArray保存每个二进制位数组中元素相加的和
        int[] bitArray = new int[32];

        int bitMask = 1;
        for (int i = 31; i >= 0; i--) {
            for (int j = 0; j < array.length; j++) {
                int bit = bitMask & array[j];
                if (bit != 0) {
                    bitArray[i] += 1;
                }
            }
            bitMask = bitMask << 1;
        }

        int res = 0;
        //将res向右移1位，然后对每个二进制位上的数字对3求余。
        for (int i = 0; i < 32; i++) {
            res = res << 1;
            res += bitArray[i] % 3;
        }

        return res;
    }


    public static void main(String[] args) throws Exception {
        int[] arr = {1, 3, 3, 3, 2, 2, 2, 1, 1, 4, 4, 4, 9};
        int res = FindNumsAppearOnce(arr);
        System.out.println(res);
    }
}
