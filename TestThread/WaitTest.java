// WaitTest.java的源码
class ThreadA extends Thread {

    public ThreadA(String name) {
        super(name);
    }

    public void run() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " call notify()");
            // 唤醒当前的wait线程
            notify();
        }
    }
}

public class WaitTest {

    public static void main(String[] args) {

        ThreadC t1 = new ThreadC("t1");

        synchronized (t1) {
            try {
                // 启动“线程t1”
                System.out.println(Thread.currentThread().getName() + " start t1");
                t1.start();

                // 主线程等待t1通过notify()唤醒。
                System.out.println(Thread.currentThread().getName() + " wait()");
                //wait()的作用是让“当前线程”等待，而“当前线程”是指正在cpu上运行的线程！这里也就是主线程，主线程变成阻塞状态
                t1.wait();

                System.out.println(Thread.currentThread().getName() + " continue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}