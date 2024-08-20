package chapter10.exam06;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SubmitRunnableExample {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // submit() -> return void -> future.get() == null
        Future<?> future = executorService.submit(() -> {
            System.out.println("비동기 작업 실행");
        });

        try {
            Object result = future.get();
            System.out.println("result=" + result); // null
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        Future<Integer> future2 = executorService.submit(() -> {
            System.out.println("비동기 작업 실행");
        }, 100);
        try {
            Integer result = future2.get();
            System.out.println("result=" + result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

    }
}
