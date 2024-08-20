package chapter10.exam05;

import java.util.concurrent.*;

public class FutureCancelExample {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Callable<Integer> callableTask = () -> {
            System.out.println("비동기 작업 시작...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException("인터럽트 걸림");
            }
            System.out.println("비동기 작업 완료");
            return 42;
        };

        Future<Integer> future = executorService.submit(callableTask);
        Thread.sleep(1000);
//        future.cancel(true);
        future.cancel(false);

        try {
            Integer result = future.get();
            System.out.println("result=" + result);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (CancellationException e) {
            throw new RuntimeException(e);
        }
    }
}
