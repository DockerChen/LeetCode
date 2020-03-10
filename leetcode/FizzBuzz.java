import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

class FizzBuzz {

    private Semaphore semaphore = new Semaphore(1);
    private int curNumber = 1;

    private int n;

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while (true) {
            this.semaphore.acquire();
            try {
                if (this.curNumber > n) {
                    return;
                }

                if ((this.curNumber % 3 == 0) && (this.curNumber % 5 != 0)) {
                    printFizz.run();
                    this.curNumber++;
                }
            } finally {
                // Release the permit anyway.
                this.semaphore.release(1);
            }

        }

    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (true) {
            this.semaphore.acquire();
            try {
                if (this.curNumber > n) {
                    return;
                }

                if ((this.curNumber % 5 == 0) && (this.curNumber % 3 != 0)) {
                    printBuzz.run();
                    this.curNumber++;
                }
            } finally {
                // Release the permit anyway.
                this.semaphore.release(1);
            }

        }

    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (true) {
            this.semaphore.acquire();
            try {
                if (this.curNumber > n) {
                    return;
                }

                if ((this.curNumber % 3 == 0) && (this.curNumber % 5 == 0)) {
                    printFizzBuzz.run();
                    this.curNumber++;
                }
            } finally {
                // Release the permit anyway.
                this.semaphore.release(1);
            }

        }

    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            this.semaphore.acquire();
            try {
                if (this.curNumber > n) {
                    return;
                }

                if ((this.curNumber % 3 != 0) && (this.curNumber % 5 != 0)) {
                    printNumber.accept(curNumber);
                    this.curNumber++;
                }
            } finally {
                // Release the permit anyway.
                this.semaphore.release(1);
            }

        }

    }
}