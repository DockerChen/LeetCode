public class ThreadPrintABC_2 {
    private static boolean startA=true;
    private static boolean startB=false;
    private static boolean startC=false;

    public static void main(String[] args) {
        //作为锁对象
        final Object object=new Object();
        //A线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object){
                    for (int i = 0; i < 10; ) {
                        if(startA){
                            System.out.print(Thread.currentThread().getName());
                            startA=false;
                            startB=true;
                            startC=false;
                            //唤醒其他线程
                            object.notifyAll();
                            i++;
                        }else {
                            try {
                                object.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }

            }
        },"A").start();

        //B线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object){
                    for (int i = 0; i < 10; ) {
                        if(startB){
                            System.out.print(Thread.currentThread().getName());
                            startA=false;
                            startB=false;
                            startC=true;
                            //唤醒其他线程
                            object.notifyAll();
                            i++;
                        }else {
                            try {
                                object.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }

            }
        },"B").start();

        //A线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object){
                    for (int i = 0; i < 10; ) {
                        if(startC){
                            System.out.print(Thread.currentThread().getName());
                            startA=true;
                            startB=false;
                            startC=false;
                            //唤醒其他线程
                            object.notifyAll();
                            i++;
                        }else {
                            try {
                                object.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }

            }
        },"C").start();
    }
}
