import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class DinnerPhillosophers_2 {

    private ReentrantLock[] locks = new ReentrantLock[5];

    private ReentrantLock pickBothForks = new ReentrantLock();

    public DinnerPhillosophers_2() {
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        //左右两边叉子的编号
        int leftFork = (philosopher + 1) % 5;
        int rightFork = philosopher;

        pickBothForks.lock();
        locks[leftFork].lock();
        pickLeftFork.run();
        locks[rightFork].lock();
        pickRightFork.run();

        eat.run();

        pickBothForks.unlock();

        //放下左边的叉子
        putLeftFork.run();
        locks[leftFork].unlock();
        //放下右边的叉子
        putRightFork.run();
        locks[rightFork].unlock();

    }

}
