import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class DinnerPhillosophers_3 {

    private ReentrantLock[] locks = new ReentrantLock[5];

    public DinnerPhillosophers_3() {
        for (int i = 0; i < 5; i++) {
            locks[i] = new ReentrantLock();
        }
    }

    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        //左右两边叉子的编号
        int leftFork = (philosopher + 1) % 5;
        int rightFork = philosopher;

        //编号为偶数的哲学家，优先拿起左边的叉子，再拿起右边的叉子
        if (philosopher % 2 == 0) {
            locks[leftFork].lock();    //拿起左边的叉子
            locks[rightFork].lock();    //拿起右边的叉子
        } else {
            locks[rightFork].lock();    //拿起右边的叉子
            locks[leftFork].lock();    //拿起左边的叉子
        }

        pickLeftFork.run();    //拿起左边的叉子 的具体执行
        pickRightFork.run();    //拿起右边的叉子 的具体执行

        eat.run();    //吃意大利面 的具体执行

        putLeftFork.run();    //放下左边的叉子 的具体执行
        putRightFork.run();    //放下右边的叉子 的具体执行

        locks[leftFork].unlock();    //放下左边的叉子
        locks[rightFork].unlock();    //放下右边的叉子

        Semaphore semaphore=new Semaphore(2);
        semaphore.acquire(2);
        semaphore.release(2);
        semaphore.tryAcquire(2, 1L,TimeUnit.SECONDS);
    }

}
