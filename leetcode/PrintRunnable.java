import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class PrintRunnable implements Runnable {
    private String str;
    private int m;
    private Lock lock;
    private Condition cur;
    private Condition succeed;

    public PrintRunnable(String str, int m, Lock lock, Condition cur, Condition succeed) {
        this.str = str;
        this.m = m;
        this.lock = lock;
        this.cur = cur;
        this.succeed = succeed;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            for (int i = 0; i < m; i++) {
                System.out.print(str);
                succeed.signal();
                cur.await();

            }
            succeed.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
