class MyThread1_2 extends Thread {

    public MyThread1_2(String name) {
        super(name);
    }

    @Override
    public void run() {
        //this表示MyThread1_2对象
        synchronized (this) {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100); // 休眠100ms
                    System.out.println(Thread.currentThread().getName() + " loop " + i);
                }
            } catch (InterruptedException ie) {
            }
        }
    }
}

public class Demo1_2 {

    public static void main(String[] args) {
        Thread t1 = new MyThread1_2("t1");  // 新建“线程t1”
        Thread t2 = new MyThread1_2("t2");  // 新建“线程t2”
        t1.start();                          // 启动“线程t1”
        t2.start();                          // 启动“线程t2” 
    }
}