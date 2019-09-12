/**
 * Creates ten threads to search for the maximum value of a large matrix.
 * Each thread searches one portion of the matrix.
 */
public class TenThreads {

    private static class WorkerThread extends Thread {
        int max = Integer.MIN_VALUE;
        int[] ourArray;

        public WorkerThread(int[] ourArray) {
            this.ourArray = ourArray;
        }

        // Find the maximum value in our particular piece of the array
        public void run() {

            for (int i = 0; i < ourArray.length; i++) {
                System.out.print(this.getName()+":"+ourArray[i]+" ");

            }
            System.out.println();

            for (int i = 0; i < ourArray.length; i++)
                max = Math.max(max, ourArray[i]);
        }

        public int getMax() {
            return max;
        }
    }

    public static int[][] getBigHairyMatrix() {
        int[][] arr = new int[10][10];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = (int) (Math.random() * 20 + 1);

            }

        }
        return arr;

    }

    public static void main(String[] args) {
        WorkerThread[] threads = new WorkerThread[10];
        int[][] bigMatrix = getBigHairyMatrix();
        int max = Integer.MIN_VALUE;

        // Give each thread a slice of the matrix to work with
        for (int i = 0; i < 10; i++) {
            threads[i] = new WorkerThread(bigMatrix[i]);
            threads[i].start();
        }

        // Wait for each thread to finish
        try {
            for (int i = 0; i < 10; i++) {
                threads[i].join();
                max = Math.max(max, threads[i].getMax());
            }
        } catch (InterruptedException e) {
            // fall through
        }

        System.out.println("Maximum value was " + max);
    }
}