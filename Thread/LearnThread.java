public class LearnThread {
    public static class HelloThread extends Thread{
        @Override
        public void run(){
            System.out.println(Thread.currentThread().getName());
            System.out.println("hello thread");
        }
    }

    public static class HelloRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            System.out.println("hello thread");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread helloThread=new HelloThread();
        helloThread.start();
        helloThread.run();
        helloThread.join();

        Thread helloRunnable=new Thread(new HelloRunnable());
        helloRunnable.start();


    }
}



/*
* result：
    main
    hello thread
    Thread-0
    hello thread
* */