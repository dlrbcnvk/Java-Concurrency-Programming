package chapter10.exam08;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class InvokeAllExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Callable<Integer>> tasks = new ArrayList<>();

        tasks.add(() -> {
            Thread.sleep(3000);
            return 1;
        });
        tasks.add(() -> {
            Thread.sleep(2000);
            return 2;
        });
        tasks.add(() -> {
            Thread.sleep(1000);
//            return 3;
            throw new RuntimeException("invokeAll error");
        });

        long started = System.currentTimeMillis();
        try {
            List<Future<Integer>> futures = executorService.invokeAll(tasks);
            for (Future<Integer> future : futures) {
                Integer result = future.get();
                System.out.println("result=" + result);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("총 소요시간:" + (System.currentTimeMillis() - started) + "ms");
            executorService.shutdown();
        }
    }
}
