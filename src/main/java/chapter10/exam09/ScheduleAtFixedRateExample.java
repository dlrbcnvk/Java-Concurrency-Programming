package chapter10.exam09;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduleAtFixedRateExample {
    public static void main(String[] args) {

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);

        Runnable task = () -> {
            try {
                Thread.sleep(2000);
                System.out.println("[" + Thread.currentThread().getName() + "]");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(task, 1, 4, TimeUnit.SECONDS);

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        future.cancel(true);
        scheduler.shutdown();
    }
}
