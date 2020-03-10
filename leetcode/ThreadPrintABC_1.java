import java.util.concurrent.locks.LockSupport;

public class ThreadPrintABC_1 {
    static Thread threadA, threadB, threadC;

    public static void main(String[] args) {
        threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    // 打印当前线程名称
                    System.out.print(Thread.currentThread().getName());
                    // 唤醒下一个线程
                    LockSupport.unpark(threadB);
                    // 当前线程阻塞
                    LockSupport.park();
                }
            }
        }, "A");
        threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    // 先阻塞等待被唤醒
                    LockSupport.park();
                    System.out.print(Thread.currentThread().getName());
                    // 唤醒下一个线程
                    LockSupport.unpark(threadC);
                }

            }
        }, "B");

        threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    // 先阻塞等待被唤醒
                    LockSupport.park();
                    System.out.print(Thread.currentThread().getName());
                    // 唤醒下一个线程
                    LockSupport.unpark(threadA);
                }

            }
        }, "C");

        threadA.start();
        threadB.start();
        threadC.start();
    }

}

