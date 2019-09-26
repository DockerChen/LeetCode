// SleepTest.java的源码
class ThreadC extends Thread{
    public ThreadC(String name){
        super(name);
    }
    public synchronized void run() {
        try {
            for(int i=0; i <10; i++){
                System.out.printf("%s: %d\n", this.getName(), i);
                // i能被4整除时，休眠2000毫秒
                if (i%4 == 0)
                    Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class SleepTest{
    public static void main(String[] args){
        ThreadC t1 = new ThreadC("t1");
        t1.start();
    }
}