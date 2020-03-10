import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

class ZeroEvenOdd {
    private Semaphore semaphoreZero = new Semaphore(1);
    private Semaphore semaphoreEven = new Semaphore(0);
    private Semaphore semaphoreOdd = new Semaphore(0);
    private int curNumber = 1;

    private int n;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
         while (true){
            this.semaphoreZero.acquire();
            try {
                if (this.curNumber > n) {
                    //释放资源，否则会导致其他线程一直等待，程序不能结束
                    this.semaphoreEven.release();
                    this.semaphoreOdd.release();
                    return;
                }

                printNumber.accept(0);

            } finally {
                // Release the permit anyway.
                if (curNumber % 2 == 0) {

                    this.semaphoreEven.release();
                } else {
                    this.semaphoreOdd.release();
                }
            }

        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            this.semaphoreEven.acquire();
            try {
                if (this.curNumber > n) {
                    return;
                }

                if (this.curNumber % 2 == 0) {
                    printNumber.accept(curNumber);
                    this.curNumber++;
                }
            } finally {

                // Release the permit anyway.
                this.semaphoreZero.release();
            }

        }

    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            this.semaphoreOdd.acquire();
            try {
                if (this.curNumber > n) {
                    return;
                }

                if (this.curNumber % 2 != 0) {
                    printNumber.accept(curNumber);
                    this.curNumber++;
                }
            } finally {

                // Release the permit anyway.
                this.semaphoreZero.release();
            }

        }

    }
}