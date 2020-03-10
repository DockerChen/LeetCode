import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestCC {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> lists = new ArrayList<>();
        HashMap<String, List<String>> hashMap = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
//            System.out.println(String.valueOf(chars));
            String ss = String.valueOf(chars);
            List<String> list = new ArrayList<>();
            if (hashMap.containsKey(ss)) {
                list = hashMap.get(ss);
            }
            list.add(str);
            hashMap.put(ss, list);
        }

        //遍历key
        Iterator<String> iterator = hashMap.keySet().iterator();
        while (iterator.hasNext()) {
            lists.add(hashMap.get(iterator.next()));
        }
        return lists;

    }

    public void testHashMap() {

        Map<String, String> map = new HashMap<String, String>();
        String key, value;
        for (int i = 1; i <= 10; i++) {
            key = "" + i;
            value = "value";
            map.put(key, value);
        }
        //遍历Entry
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            key = entry.getKey();
            value = entry.getValue();
        }
        //遍历key
        Iterator<String> iterator1 = map.keySet().iterator();
        while (iterator1.hasNext()) {
            key = iterator1.next();
        }
        //遍历value
        Iterator<String> iterator2 = map.values().iterator();
        while (iterator2.hasNext()) {
            value = iterator2.next();
        }
    }

    public void mergesort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        mergesort(arr, 0, arr.length - 1);
    }

    public void mergesort(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = ((right - left) >> 1) + left;
        System.out.println(mid);
        mergesort(arr, left, mid);
        mergesort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    public void merge(int[] arr, int left, int mid, int right) {
        int p1 = left, p2 = mid + 1;
        int[] res = new int[right - left + 1];
        int i = 0;
        while (p1 <= mid && p2 <= right) {
            res[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            res[i++] = arr[p1++];
        }
        while (p2 <= right) {
            res[i++] = arr[p2++];
        }

        for (int j = 0; j < res.length; j++) {
            arr[left + j] = res[j];
        }

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        MyCallable mc=new MyCallable();
        FutureTask<Integer> ft=new FutureTask<>(mc);
        Thread thread1=new Thread(ft);
        thread1.start();
        System.out.println(ft.get());
    }

}



class MyCallable implements Callable<Integer>{
    public Integer call(){
        return 123;
    }
}
