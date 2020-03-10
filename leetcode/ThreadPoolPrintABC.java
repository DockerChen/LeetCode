import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolPrintABC {
    private static final int CORE_POOL_SIZE = 10;
    private static final int MAX_POOL_SIZE = 20;
    private static final int QUEUE_CAPACITY = 100;
    private static final Long KEEP_ALIVE_TIME = 1L;
    private static final int N = 10;
    private static final int M = 10;
    private static final List<Semaphore> semaphoreList = new ArrayList<>();

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy());

        Semaphore semaphore;
        semaphore = new Semaphore(1);

        semaphoreList.add(semaphore);

        for (int i = 1; i < N; i++) {
            semaphore = new Semaphore(0);
            semaphoreList.add(semaphore);
        }

        for (int i = 0; i < N; i++) {
            char c = (char) ('A' + i);
            String str = "" + c;
            int j = i + 1;
            if (i == N - 1) {
                j = 0;
            }
            Semaphore cur = semaphoreList.get(i);
            Semaphore succeed = semaphoreList.get(j);

            Runnable worker = new MyRunnable(str, M, cur, succeed);
            executor.execute(worker);
        }
        executor.shutdown();


    }

}
