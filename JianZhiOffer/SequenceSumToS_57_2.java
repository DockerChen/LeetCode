import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class SequenceSumToS_57_2 {

    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        int small = 1;
        int big = 2;
        
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        while (small <= (1 + sum) / 2) {

            int curSum = getCurSum(small, big);
            if (curSum == sum) {
                ArrayList<Integer> arrayList = new ArrayList<>();
                for (int i = small; i <= big; i++) {
                    arrayList.add(i);
                }
                arrayLists.add(arrayList);
                big++;
            } else if (curSum < sum) {
                big++;
            } else {
                small++;
            }
        }
        return arrayLists;

    }

    private int getCurSum(int small, int big) {
        int curSum = (big - small + 1) * (small + big) / 2;
        return curSum;
    }

    public static void main(String[] args) {
        SequenceSumToS_57_2 test=new SequenceSumToS_57_2();
        ArrayList<ArrayList<Integer>> arrayLists= test.FindContinuousSequence(90);
        for (ArrayList<Integer> arrayList:arrayLists) {
            System.out.println(arrayList.toString());

        }
        char[] c={'a','b','c'};
        System.out.println(String.valueOf(c).length());


    }
}
