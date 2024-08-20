package chapter10.exam09;

import java.util.concurrent.*;

public class ScheduleCallableExample {
    public static void main(String[] args) {

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

//        Callable<String> task = () -> {
//            return "작업이 한 번 실행되고 결과를 반환합니다.";
//        };

        Runnable task = () -> {
            System.out.println("작업이 한 번 실행되고 결과를 반환합니다.");
        };

        ScheduledFuture<?> future = scheduler.schedule(task, 3, TimeUnit.SECONDS);
        try {
            String result = (String) future.get();
            System.out.println("result=" + result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            scheduler.shutdown();
        }
    }
}
