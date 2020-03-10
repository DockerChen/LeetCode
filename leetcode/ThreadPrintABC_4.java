import java.util.concurrent.Semaphore;

public class ThreadPrintABC_4 {
    public static void main(String[] args) {
        Semaphore semaphoreA=new Semaphore(1);
        Semaphore semaphoreB=new Semaphore(0);
        Semaphore semaphoreC=new Semaphore(0);

        Thread threadA=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        semaphoreA.acquire();
                        System.out.print(Thread.currentThread().getName());
                        semaphoreB.release();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A");
        Thread threadB=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        semaphoreB.acquire();
                        System.out.print(Thread.currentThread().getName());
                        semaphoreC.release();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B");

        Thread threadC=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        semaphoreC.acquire();
                        System.out.print(Thread.currentThread().getName());
                        semaphoreA.release();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C");

        threadA.start();
        threadB.start();
        threadC.start();


    }
}
