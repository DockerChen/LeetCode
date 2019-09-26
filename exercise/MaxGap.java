import java.awt.dnd.DragSourceAdapter;
import java.util.Arrays;

public class MaxGap {
    //        生成随机数组
    public static int[] generateArray() {
        int[] arr = new int[(int) (Math.random() * 20)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 20);
        }
        return arr;
    }

    //得到该数据所属桶的下标
    public static int getBucket(int length, int num, int min, int max) {
        return (int) ((num - min) * length / (max - min));

    }

    //得到相邻两数的最大差值
    private static int getMaxGap(int[] arr) {
        if(arr==null||arr.length<2){
            return 0;
        }
        int[] bucket_max = new int[arr.length + 1];
        int[] bucket_min = new int[arr.length + 1];
        int[] bucket = new int[arr.length + 1];
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

//        遍历得到数组的最大和最小值



        for (int i = 0; i < arr.length; i++) {
            max = arr[i] > max ? arr[i] : max;
            min = arr[i] < min ? arr[i] : min;
        }
//        需要判断最大值和最小值是否相等，相等的话直接返回0，否则之后的划分会抛出异常
        if(min==max){
            return 0;
        }
//        根据最小和最大值划分成n+1个桶，将元素放到对应的桶中，并更新bucket_max,bucket_min,bucket数组
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            index = getBucket(arr.length, arr[i], min, max);
            bucket_max[index] = bucket[index] == 1 ? Math.max(arr[i], bucket_max[index]) : arr[i];
            bucket_min[index] = bucket[index] == 1 ? Math.min(arr[i], bucket_min[index]) : arr[i];
            bucket[index] = 1;
        }

        int res = 0;
        int lastMax = bucket_max[0];
        for (int i = 1; i <= arr.length; i++) {
            if (bucket[i] == 1) {
                res = Math.max(res, bucket_min[i] - lastMax);
                lastMax = bucket_max[i];
            }

        }
        return res;
    }

    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        Arrays.sort(arr);
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            res = (arr[i] - arr[i - 1]) > res ? arr[i] - arr[i - 1] : res;
        }
        return res;


    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5000; i++) {
            int[] arr = generateArray();
            int[] arr2=copyArray(arr);

            if(getMaxGap(arr)!=comparator(arr2)){
                System.out.println("fuck");
                break;
            }

        }
        System.out.println("Nice");




    }
}
