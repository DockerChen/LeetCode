public class PrintStringSequence_38 {
    public static void printStringSequence(char[] chs, int index) {
        if (index == chs.length - 1) {
            System.out.print(String.valueOf(chs) + " ");
            return;
        }

        for (int i = index; i < chs.length; i++) {
            //确定第一个字符，求后面字符串的全排列
            swap(chs, index, i);
            printStringSequence(chs, index + 1);
            //全排列完了，交换回来，保持原有顺序不变
            swap(chs, index, i);
        }

    }

    private static void swap(char[] chs, int index, int i) {
        char c = chs[index];
        chs[index] = chs[i];
        chs[i] = c;
    }

    public static void main(String[] args) {
        char[] chs="abc".toCharArray();
        printStringSequence(chs,0);

        /*
        * result：abc acb bac bca cba cab
         * */
    }


}
