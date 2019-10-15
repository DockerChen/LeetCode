import java.time.LocalTime;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Schedule {
    public static void main(String[] args) {
        ScheduledExecutorService executorService= Executors.newScheduledThreadPool(3);
        executorService.scheduleAtFixedRate(new HelloTask("Bob"),2,5, TimeUnit.SECONDS);
        executorService.scheduleWithFixedDelay(new HelloTask("Alice"),2,5,TimeUnit.SECONDS);
    }

}

class HelloTask implements Runnable {
    String name;

    public HelloTask(String name) {
        this.name = name;

    }

    @Override
    public void run() {
        System.out.println("hello, " + name + "! It is " + LocalTime.now());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("GoodBye, " + name + "! It is " + LocalTime.now());

    }
}
