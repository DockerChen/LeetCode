import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class PrintStr {
    public static void main(String[] args) throws InterruptedException {
//        char c;
//        for (int i = 0; i < 10; i++) {
//            c=(char)('A'+i);
//            System.out.println(c);
//        }
        Semaphore semaphore;
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            semaphore = new Semaphore(1);
            System.out.println(semaphore);
            semaphore.acquire();
            System.out.println(semaphore);
            semaphore.release();
            System.out.println(semaphore);
        }

//        Semaphore[] semaphores=new Semaphore[10];
//        for (int i = 0; i < 10; i++) {
//            System.out.println(i);
//            semaphores[i]=new Semaphore(1);
//            System.out.println(semaphores[i]);
//            semaphores[i].acquire();
//            System.out.println(semaphores[i]);
//            semaphores[i].release();
//            System.out.println(semaphores[i]);
//        }

        Semaphore[] semaphores_1 = new Semaphore[10];
        Arrays.fill(semaphores_1, new Semaphore(1));
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            System.out.println(semaphores_1[i]);
            semaphores_1[i].acquire();
            System.out.println(semaphores_1[i]);
            semaphores_1[i].release();
            System.out.println(semaphores_1[i]);
        }

        int[] arr = new int[10];
        Arrays.fill(arr, 0);
//        for (int i = 0; i < 10; i++) {
//            System.out.println(System.identityHashCode(arr[i]));
//        }
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                arr[i] = i;
            }
            System.out.println(System.identityHashCode(arr[i]));
        }

        arr[0] = 50;
        arr[1] = 50;
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.println(System.identityHashCode(arr[i]));
        }

//        Thread[] threads = new TestThread[10];
//        for (int i = 0; i < 10; i++) {
//            threads[i] = new TestThread("" + i, i);
//            System.out.println(System.identityHashCode(threads[i]));
//            threads[i].start();
//
//        }

    }
}

class TestThread extends Thread {
    String name;
    int i;

    public TestThread(String name, int i) {
        this.name = name;
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " " + i);
    }
}
