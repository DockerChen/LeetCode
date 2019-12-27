import java.util.HashSet;

public class PrintStringPermutationAndRemoveRepetition {
    //不去重，打印所有的全排序序列
    public static int count = 0;

    public static void permutation(char[] chs, int index) {
        if (index == chs.length - 1) {
            System.out.print(String.valueOf(chs) + " ");
            return;
        }
        /*
        从集合中依次选出每一个元素，作为排列的第一个元素，然后对剩余的元素进行全排列，如此递归处理，从而得到所有元素的全排列。
        以对字符串 abc 进行全排列为例，我们可以这么做：以 abc 为例。
            固定 a，求后面 bc 的排列：abc，acb，求好后，a 和 b 交换，得到 bac
            固定 b，求后面 ac 的排列：bac，bca，求好后，c 放到第一位置，得到 cba
            固定 c，求后面 ba 的排列：cba，cab。
        */

        count++;

        for (int i = index; i < chs.length; i++) {
            //确定第一个位置的字符，求后面字符的全排列
            swap(chs, index, i);
            permutation(chs, index + 1);
            System.out.println(count + "-chs:" + String.valueOf(chs));
            swap(chs, index, i);
            System.out.println(count + "+chs:" + String.valueOf(chs));
        }

    }

    //去重
    public static void permutation(char[] chs, int index, HashSet hashSet) {
        if (index == chs.length - 1) {
            if (!hashSet.contains(String.valueOf(chs))) {
                hashSet.add(String.valueOf(chs));
                System.out.print(String.valueOf(chs) + " ");
            }
            return;
        }

        for (int i = index; i < chs.length; i++) {
            swap(chs, index, i);
            permutation(chs, index + 1, hashSet);
            swap(chs, index, i);

        }

    }

    public static void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;

    }

    public static void main(String[] args) {
        permutation("abc".toCharArray(), 0);
        System.out.println();
        permutation("acc".toCharArray(), 0, new HashSet());

    }

}
