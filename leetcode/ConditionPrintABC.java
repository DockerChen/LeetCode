import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionPrintABC {
    private static final int CORE_POOL_SIZE = 10;
    private static final int MAX_POOL_SIZE = 20;
    private static final int QUEUE_CAPACITY = 100;
    private static final Long KEEP_ALIVE_TIME = 1L;
    private static final int N = 10;
    private static final int M = 10;
    private static final List<Condition> conditionList = new ArrayList<>();
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < N; i++) {
            Condition condition = lock.newCondition();
            conditionList.add(condition);
        }

        for (int i = 0; i < N; i++) {
            char c = (char) ('A' + i);
            String str = "" + c;
            int j = i + 1;
            if (i == N - 1) {
                j = 0;
            }
            Condition cur = conditionList.get(i);
            Condition succeed = conditionList.get(j);
            Runnable worker = new PrintRunnable(str, M, lock, cur, succeed);
            executor.execute(worker);
        }
        executor.shutdown();
    }
}
