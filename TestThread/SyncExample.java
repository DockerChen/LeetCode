import java.util.Date;

public class SyncExample {
    public static class Thingie {

        private Date lastAccess;

        public synchronized void setLastAccess(Date date) {
            this.lastAccess = date;
        }
    }

    public static class MyThread extends Thread {
        private Thingie thingie;

        public MyThread(Thingie thingie) {
            this.thingie = thingie;
        }

        public void run() {
            thingie.setLastAccess(new Date());
            System.out.println(this.getName()+" "+thingie.lastAccess);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thingie thingie1 = new Thingie(),
                thingie2 = new Thingie();

        new MyThread(thingie1).start();

        Thread.sleep(1000);
        new MyThread(thingie2).start();
    }
}