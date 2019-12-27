public class EightQueens_38_2 {

    public static int count = 0;

    public static void process(int[] ColumnIndex, int index) {
        if (index == ColumnIndex.length - 1) {

            for (int i = 0; i < ColumnIndex.length; i++) {
                for (int j = 0; j < ColumnIndex.length; j++) {
                    //判断两个棋子是否在哎同一对角线上
                    if ((i != j) && (Math.abs(i - j) == Math.abs(ColumnIndex[i] - ColumnIndex[j]))) {
                        return;
                    }
                }
            }
            count++;
            return;
        }

        for (int i = index; i < ColumnIndex.length; i++) {
            swap(ColumnIndex, index, i);
            process(ColumnIndex, index + 1);
            swap(ColumnIndex, index, i);

        }

    }

    private static void swap(int[] ColumnIndex, int index, int i) {
        int tmp = ColumnIndex[index];
        ColumnIndex[index] = ColumnIndex[i];
        ColumnIndex[i] = tmp;
    }

    public static void main(String[] args) {
        int[] ColumnIndex = {0, 1, 2, 3, 4, 5, 6, 7};
        process(ColumnIndex, 0);
        System.out.println(count);
    }

}
