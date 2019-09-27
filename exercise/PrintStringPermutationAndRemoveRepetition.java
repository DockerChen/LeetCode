import java.util.HashSet;

public class PrintStringPermutationAndRemoveRepetition {
    //不去重
    public static void permutation(char[] chs, int index) {
        if (index == chs.length - 1) {
            System.out.print(String.valueOf(chs) + " ");
            return;
        }

        for (int i = index; i < chs.length; i++) {
            swap(chs, index, i);
            permutation(chs, index + 1);
            swap(chs, index, i);

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
        permutation("acc".toCharArray(), 0);
        System.out.println();
        permutation("acc".toCharArray(), 0, new HashSet());

    }

}
