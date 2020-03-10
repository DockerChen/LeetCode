import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class DinnerPhilosophers_1 {
    private ReentrantLock[] locks = new ReentrantLock[5];

    private Semaphore eatLimit = new Semaphore(4);

    public DinnerPhilosophers_1() {
        for (int i = 0; i < 5; i++) {
            locks[i] = new ReentrantLock();
        }

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
        //限制人数-1
        eatLimit.acquire();
        //拿起左边的叉子
        locks[leftFork].lock();
        pickLeftFork.run();
        //拿起右边的叉子
        locks[rightFork].lock();
        pickRightFork.run();
        //吃面
        eat.run();
        //放下左边的叉子
        putLeftFork.run();
        locks[leftFork].unlock();
        //放下右边的叉子
        putRightFork.run();
        locks[rightFork].unlock();

        //限制人数+1
        eatLimit.release();

    }

}
