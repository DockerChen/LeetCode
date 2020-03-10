import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPrintABC_3 {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    for (int i = 0; i < 10; i++) {
                        System.out.print(Thread.currentThread().getName());
                        conditionB.signal();
                        conditionA.await();
                    }
                    conditionB.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    for (int i = 0; i < 10; i++) {
                        System.out.print(Thread.currentThread().getName());
                        conditionC.signal();

                        conditionB.await();

                    }
                    conditionC.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }, "B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    for (int i = 0; i < 10; i++) {
                        System.out.print(Thread.currentThread().getName());
                        conditionA.signal();

                        conditionC.await();

                    }
                    conditionA.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }, "C").start();
    }

}
