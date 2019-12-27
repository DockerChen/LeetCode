import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class DataStreamMedian {
    public static class Median {
        private PriorityQueue<Integer> minHeap;
        private PriorityQueue<Integer> maxHeap;

        public Median() {
            minHeap = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });
            maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });

        }

        public void Insert(int number) {
            if (maxHeap.isEmpty()) {
                maxHeap.add(number);
            } else {
                if (maxHeap.peek() >= number) {
                    maxHeap.add(number);
                } else if (minHeap.isEmpty()) {
                    minHeap.add(number);
                } else if (minHeap.peek() <= number) {
                    minHeap.add(number);

                } else {
                    maxHeap.add(number);
                }
            }

            modifyHeap();

        }

        public void modifyHeap() {
            if (maxHeap.size() == minHeap.size() + 2) {
                minHeap.add(maxHeap.poll());
            }
            if (minHeap.size() == maxHeap.size() + 2) {
                maxHeap.add(minHeap.poll());
            }
        }

        public double GetMedian() {
            int minHeapSize = minHeap.size();
            int maxHeapSize = maxHeap.size();
            double median = 0;
            if ((minHeapSize + maxHeapSize) % 2 == 0) {
                median = (minHeap.peek() + maxHeap.peek()) / 2.0;
            } else {
                median = minHeapSize > maxHeapSize ? minHeap.peek() : maxHeap.peek();
            }

            return median;

        }

    }

    public static int[] generateArray() {
        int[] array = new int[(int) (Math.random() * 10) + 1];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 11);
        }
        return array;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

    }

    public static double getMeDianArray(int[] arr) {
        Arrays.sort(arr);
        double median = 0;
        if (arr.length % 2 == 0) {
            median = (arr[arr.length / 2 - 1] + arr[arr.length / 2]) / 2.0;
        } else {
            median = arr[arr.length / 2];
        }
        return median;
    }

    public static void main(String[] args) {
        int testingFrequency = 100000;
        boolean flag = true;
        for (int i = 0; i < testingFrequency; i++) {
            int[] arr = generateArray();
            Median median = new Median();
            for (int j = 0; j < arr.length; j++) {
                median.Insert(arr[j]);
            }
            if (median.GetMedian() != getMeDianArray(arr)) {
                flag = false;
                printArray(arr);
                break;
            }

        }

        System.out.println(flag ? "good!" : "fuck!");



    }

}
