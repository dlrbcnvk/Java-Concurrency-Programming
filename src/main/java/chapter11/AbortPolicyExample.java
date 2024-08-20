package chapter11;

import java.util.concurrent.*;

public class AbortPolicyExample {
    public static void main(String[] args) {
        int corePoolSize = 2;
        int maxPoolSize = 2;
        long keepAliveTime = 0;
        int workQueueCapacity = 2;

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(workQueueCapacity),
                new ThreadPoolExecutor.AbortPolicy());

        submitTasks(executor);
    }

    private static void submitTasks(ThreadPoolExecutor executor) {
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("[" + Thread.currentThread().getName() + "] Task " + taskId + " is running on thread");
            });
        }

        executor.shutdown();
    }
}

class MyRejectedExecutionHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        if (!executor.isShutdown()) {
            executor.getQueue().poll();
            executor.getQueue().offer(r);
        }
    }
}
