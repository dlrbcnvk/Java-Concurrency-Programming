package chapter10.exam10;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExample {
    public static void main(String[] args) {

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            System.out.println("Task is running...");
        };

        int initialDelay = 0; // 초기 지연 (바로 실행)
        int initialPeriod = 1; // 초기 주기 (초단위)
        int updatedPeriod = 3; // 변경된 주기 (초단위)

        // 초기 스케줄링
        ScheduledFuture<?> future = scheduledExecutorService.scheduleAtFixedRate(task, initialDelay, initialPeriod, TimeUnit.SECONDS);

        try {
            Thread.sleep(5000); // 5초동안 실행
            future.cancel(true); // 스케줄링 취소

            // 변경된 주기로 다시 스케줄링

            future = scheduledExecutorService.scheduleAtFixedRate(task, 0, updatedPeriod, TimeUnit.SECONDS);

            Thread.sleep(10000); // 10초동안 실행 (변경된 주기로 실행)
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            // 작업 중지 및 스레드 풀 종료
            scheduledExecutorService.shutdown();
        }
    }
}
