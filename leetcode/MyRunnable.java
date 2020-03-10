import java.util.concurrent.Semaphore;

public class MyRunnable implements Runnable {
    private String str;
    private int m;
    private Semaphore cur;
    private Semaphore succeed;

    public MyRunnable(String str, int m, Semaphore cur, Semaphore succeed) {
        this.str = str;
        this.m = m;
        this.cur = cur;
        this.succeed = succeed;
    }

    @Override
    public void run() {

        for (int k = 0; k < this.m; k++) {

            try {
                this.cur.acquire();
                System.out.print(str);
                this.succeed.release();


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}


